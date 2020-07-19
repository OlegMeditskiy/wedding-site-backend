package weddingsitebackend.weddingsitebackend.service.Impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import weddingsitebackend.weddingsitebackend.models.siteObjects.PersonalInvitation;
import weddingsitebackend.weddingsitebackend.payload.common.ApiResponse;
import weddingsitebackend.weddingsitebackend.payload.requests.PersonalInvitationRequest;
import weddingsitebackend.weddingsitebackend.payload.responses.PersonalInvitationResponse;
import weddingsitebackend.weddingsitebackend.repository.siteObjects.PersonalInvitationRepo;
import weddingsitebackend.weddingsitebackend.service.EmailService;
import weddingsitebackend.weddingsitebackend.service.PersonalInvitationService;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonalInvitationImpl implements PersonalInvitationService {
    final
    PersonalInvitationRepo personalInvitationRepo;
    final EmailService emailService;

    public PersonalInvitationImpl(PersonalInvitationRepo personalInvitationRepo, EmailService emailService) {
        this.personalInvitationRepo = personalInvitationRepo;
        this.emailService = emailService;
    }

    @Override
    public ResponseEntity<?> create(PersonalInvitationRequest personalInvitationRequest) throws MessagingException {
        PersonalInvitation personalInvitation = new PersonalInvitation();
        personalInvitation.setFirstName(personalInvitationRequest.getFirstName());
        personalInvitation.setLastName(personalInvitationRequest.getLastName());
        personalInvitation.setNeedTransfer(personalInvitationRequest.isNeedTransfer());
        personalInvitation.setWhoComingWithMe(personalInvitationRequest.getWhoComingWithMe());

        if (personalInvitationRequest.isComing()){
            personalInvitation.setComing(true);
            String to = personalInvitationRequest.getEmail();
            String subject = "Приглашение на свадьбу";
            String text = "<h2>Мы с нетерпением ждем встречи с Вами!</h2>" +
                    "<p>Мы с нетерпением ждем встречи с Вами!</p>" +
                    "<p>Вот детали события:</p>" +
                    "<p>Свадьба" +
                    "<br><u>29 августа 2020 г., 15:00</u>" +
                    "<br>Загородный клуб 'Артиленд', Московская область, Балашиха, Новское шоссе, 10.<p/>" +
                    "<p><a href='http://daria-alexander-wedding.herokuapp.com/'>Узнать больше о событии</a></p>" +
                    "<a target=\"_blank\" href=\"https://calendar.google.com/event?action=TEMPLATE&amp;tmeid=MTBoaXNlOTBwcTIzYWVkb3ZzdDNjbmY1ZzYgZGFyaWEuYWxleGFuZGVyLndlZGRpbmdAbQ&amp;tmsrc=daria.alexander.wedding%40gmail.com\">Добавить в мой календарь</a>" +
                    "<p>C уважением," +
                    "<br>Дарья и Александр</p>";
            emailService.sendMessageHTMLtags(to, subject, text);
        }
        else {
            personalInvitation.setComing(false);
        }
        personalInvitationRepo.save(personalInvitation);
        return ResponseEntity.ok().body(new ApiResponse(true, "Персональное приглашение для " + personalInvitation.getFirstName() +" "+personalInvitation.getLastName()+ " было создано"));
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(PersonalInvitationRequest personalInvitationRequest) {
        PersonalInvitation personalInvitation = personalInvitationRepo.getOne(personalInvitationRequest.getId());
        personalInvitationRepo.delete(personalInvitation);
        return ResponseEntity.ok().body(new ApiResponse(true, "Персональное приглашение для " + personalInvitation.getFirstName() +" "+personalInvitation.getLastName()+ " было удалено"));
    }

    @Override
    public List<PersonalInvitationResponse> get() {
        List<PersonalInvitation> personalInvitationList = personalInvitationRepo.findAll();
        List<PersonalInvitationResponse> personaInvitationResponseList = new ArrayList<>();
        for (PersonalInvitation personalInvitation : personalInvitationList) {
            PersonalInvitationResponse personalInvitationResponse = new PersonalInvitationResponse();
            personalInvitationResponse.setId(personalInvitation.getId());
            personalInvitationResponse.setComing(personalInvitation.isComing());
            personalInvitationResponse.setNeedTransfer(personalInvitation.isNeedTransfer());
            personalInvitationResponse.setFirstName(personalInvitation.getFirstName());
            personalInvitationResponse.setLastName(personalInvitation.getLastName());
            personalInvitationResponse.setWhoComingWithMe(personalInvitation.getWhoComingWithMe());
            personaInvitationResponseList.add(personalInvitationResponse);
        }
        return personaInvitationResponseList;
    }



}

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
            String text = "<h1>Вы приняли наше приглашение на свадьбу!</h1>" +
                    "<p>Ждем вас <b>29 августа в 15:00</b> в загородном клубе Артиленд по адресу 'Московская область, Балашиха, Новское шоссе, 10.'<p/>" +
                    "<p>Дополнительная информация о свадьбе находится на нашем <a href='http://daria-alexander-wedding.herokuapp.com/'>свадебном сайте</a></p>" +
                    "<br><br>C уважением,<br>" +
                    "Дарья и Александр";
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

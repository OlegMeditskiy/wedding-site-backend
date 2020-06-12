package weddingsitebackend.weddingsitebackend.service.Impl;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import weddingsitebackend.weddingsitebackend.models.siteObjects.InvitationStatus;
import weddingsitebackend.weddingsitebackend.models.siteObjects.PersonalInvitation;
import weddingsitebackend.weddingsitebackend.payload.common.ApiResponse;
import weddingsitebackend.weddingsitebackend.payload.requests.PersonalInvitationRequest;
import weddingsitebackend.weddingsitebackend.payload.responses.PersonalInvitationResponse;
import weddingsitebackend.weddingsitebackend.repository.siteObjects.PersonalInvitationRepo;
import weddingsitebackend.weddingsitebackend.service.PersonalInvitationService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PersonalInvitationImpl implements PersonalInvitationService {
    final
    PersonalInvitationRepo personalInvitationRepo;

    public PersonalInvitationImpl(PersonalInvitationRepo personalInvitationRepo) {
        this.personalInvitationRepo = personalInvitationRepo;
    }

    @Override
    public ResponseEntity<?> update(PersonalInvitationRequest personalInvitationRequest) {
        PersonalInvitation personalInvitation = personalInvitationRepo.getOne(personalInvitationRequest.getId());
        personalInvitation.setNames(personalInvitationRequest.getNames());
        personalInvitationRepo.save(personalInvitation);
        return ResponseEntity.ok().body(new ApiResponse(true, "Персональное приглашение было обновлено"));
    }

    @Override
    public ResponseEntity<?> create(PersonalInvitationRequest personalInvitationRequest) {
        PersonalInvitation personalInvitation = new PersonalInvitation();
        personalInvitation.setNames(personalInvitationRequest.getNames());
        UUID uniqueKey = UUID.randomUUID();
        personalInvitation.setUniqueKey(uniqueKey);
        personalInvitation.setInvitationLink("http://localhost:3000/invitation/" + uniqueKey);
        personalInvitationRepo.save(personalInvitation);
        return ResponseEntity.ok().body(getApiResponses(
                "Персональное приглашение для " + personalInvitationRequest.getNames() + " было создано",
                "Personal invitation for " + personalInvitationRequest.getNames() + " was created"
        ));
    }

    @Override
    public List<ApiResponse> getApiResponses(String response1, String response2) {
        List<ApiResponse> apiResponses = new ArrayList<>();
        ApiResponse apiResponse1 = new ApiResponse(true, response1);
        ApiResponse apiResponse2 = new ApiResponse(true, response2);
        apiResponses.add(apiResponse1);
        apiResponses.add(apiResponse2);
        return apiResponses;
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(PersonalInvitationRequest personalInvitationRequest) {
        PersonalInvitation personalInvitation = personalInvitationRepo.getOne(personalInvitationRequest.getId());
        personalInvitationRepo.delete(personalInvitation);
        return ResponseEntity.ok().body(new ApiResponse(true, "Персональное приглашение для " + personalInvitation.getNames() + " было удалено"));
    }

    @Override
    public List<PersonalInvitationResponse> get() {
        List<PersonalInvitation> personalInvitationList = personalInvitationRepo.findAll();
        List<PersonalInvitationResponse> personaInvitationResponseList = new ArrayList<>();
        for (PersonalInvitation personalInvitation : personalInvitationList) {
            PersonalInvitationResponse personalInvitationResponse = new PersonalInvitationResponse();
            personalInvitationResponse.setId(personalInvitation.getId());
            personalInvitationResponse.setStatus(personalInvitation.getStatus());
            personalInvitationResponse.setNeedTransfer(personalInvitation.isNeedTransfer());
            personalInvitationResponse.setNames(personalInvitation.getNames());
            personalInvitationResponse.setInvitationLink(personalInvitation.getInvitationLink());
            personaInvitationResponseList.add(personalInvitationResponse);
        }
        return personaInvitationResponseList;
    }

    @Override
    public PersonalInvitationResponse getPersonal(String key) {
        UUID uuid =UUID.fromString(key);
        PersonalInvitation personalInvitation =personalInvitationRepo.findByUniqueKey(uuid);
        PersonalInvitationResponse personalInvitationResponse = new PersonalInvitationResponse();
        personalInvitationResponse.setStatus(personalInvitation.getStatus());
        personalInvitationResponse.setNames(personalInvitation.getNames());
        personalInvitationResponse.setId(personalInvitation.getId());
        personalInvitationResponse.setNeedTransfer(personalInvitation.isNeedTransfer());
        return personalInvitationResponse;
    }

    @Override
    public ResponseEntity<?> accept(PersonalInvitationRequest personalInvitationRequest) {
        PersonalInvitation personalInvitation =personalInvitationRepo.getOne(personalInvitationRequest.getId());
        personalInvitation.setStatus(InvitationStatus.ACCEPTED);
        personalInvitation.setNeedTransfer(personalInvitationRequest.isNeedTransfer());
        personalInvitationRepo.save(personalInvitation);
        return ResponseEntity.ok().body(new ApiResponse(true, "Персональное приглашение для " + personalInvitation.getNames() + " было принято"));

    }
    @Override
    public ResponseEntity<?> decline(PersonalInvitationRequest personalInvitationRequest) {
        PersonalInvitation personalInvitation =personalInvitationRepo.getOne(personalInvitationRequest.getId());
        personalInvitation.setStatus(InvitationStatus.DECLINED);
        personalInvitationRepo.save(personalInvitation);
        return ResponseEntity.ok().body(new ApiResponse(true, "Персональное приглашение для " + personalInvitation.getNames() + " было не принято"));

    }
}

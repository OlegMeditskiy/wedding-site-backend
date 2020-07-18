package weddingsitebackend.weddingsitebackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import weddingsitebackend.weddingsitebackend.payload.requests.PersonalInvitationRequest;
import weddingsitebackend.weddingsitebackend.service.PersonalInvitationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/delete")
public class AdminDeleteObjectsController {
    final PersonalInvitationService personalInvitationService;

    public AdminDeleteObjectsController(PersonalInvitationService personalInvitationService) {
        this.personalInvitationService = personalInvitationService;
    }


    @DeleteMapping(value = "/personalInvitation")
    public ResponseEntity<?> delete(@Valid @RequestBody PersonalInvitationRequest personalInvitationRequest) {
        return personalInvitationService.delete(personalInvitationRequest);
    }
}

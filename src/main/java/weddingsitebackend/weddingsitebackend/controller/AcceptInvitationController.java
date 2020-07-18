package weddingsitebackend.weddingsitebackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import weddingsitebackend.weddingsitebackend.payload.requests.PersonalInvitationRequest;
import weddingsitebackend.weddingsitebackend.service.PersonalInvitationService;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/create/")
public class AcceptInvitationController {

    final PersonalInvitationService personalInvitationService;

    public AcceptInvitationController(PersonalInvitationService personalInvitationService) {
        this.personalInvitationService = personalInvitationService;
    }

    @PostMapping(value = "/invitation")
    public ResponseEntity<?> createPersonalInvitation(@Valid @RequestBody PersonalInvitationRequest personalInvitationRequest) throws MessagingException {
        return personalInvitationService.create(personalInvitationRequest);
    }
}

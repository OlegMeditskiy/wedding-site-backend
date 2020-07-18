package weddingsitebackend.weddingsitebackend.controller;

import org.springframework.web.bind.annotation.*;
import weddingsitebackend.weddingsitebackend.payload.responses.*;
import weddingsitebackend.weddingsitebackend.service.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/get")
public class AdminGetObjectsController {


    final PersonalInvitationService personalInvitationService;

    public AdminGetObjectsController(PersonalInvitationService personalInvitationService) {
        this.personalInvitationService = personalInvitationService;
    }

    @GetMapping(value = "/personalInvitations")
    public List<PersonalInvitationResponse> getPersonalInvitations() {
        return personalInvitationService.get();
    }

}

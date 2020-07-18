package weddingsitebackend.weddingsitebackend.service;

import org.springframework.http.ResponseEntity;
import weddingsitebackend.weddingsitebackend.payload.common.ApiResponse;
import weddingsitebackend.weddingsitebackend.payload.requests.PersonalInvitationRequest;
import weddingsitebackend.weddingsitebackend.payload.responses.PersonalInvitationResponse;

import java.util.List;

public interface PersonalInvitationService {

    ResponseEntity<?> create(PersonalInvitationRequest personalInvitationRequest);

    ResponseEntity<?> delete(PersonalInvitationRequest personalInvitationRequest);

    List<PersonalInvitationResponse> get();


}

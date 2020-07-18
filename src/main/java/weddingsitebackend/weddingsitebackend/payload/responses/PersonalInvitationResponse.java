package weddingsitebackend.weddingsitebackend.payload.responses;

import lombok.Getter;
import lombok.Setter;
import weddingsitebackend.weddingsitebackend.models.siteObjects.InvitationStatus;

@Setter
@Getter
public class PersonalInvitationResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private InvitationStatus status;

    private boolean needTransfer;
    private String whoComingWithMe;
    private String email;

}

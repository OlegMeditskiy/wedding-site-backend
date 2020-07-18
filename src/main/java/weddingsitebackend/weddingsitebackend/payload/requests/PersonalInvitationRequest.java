package weddingsitebackend.weddingsitebackend.payload.requests;

import lombok.Getter;
import weddingsitebackend.weddingsitebackend.models.siteObjects.InvitationStatus;

@Getter
public class PersonalInvitationRequest {

    private Long id;

    private boolean needTransfer;

    private String firstName;

    private String lastName;

    private String whoComingWithMe;

    private boolean coming;

    private String email;

}

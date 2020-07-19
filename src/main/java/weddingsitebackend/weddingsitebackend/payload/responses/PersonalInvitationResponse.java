package weddingsitebackend.weddingsitebackend.payload.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonalInvitationResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private boolean coming;

    private boolean needTransfer;
    private String whoComingWithMe;
    private String email;

}

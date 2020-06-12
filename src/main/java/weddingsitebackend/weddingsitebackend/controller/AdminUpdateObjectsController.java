package weddingsitebackend.weddingsitebackend.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import weddingsitebackend.weddingsitebackend.payload.requests.*;
import weddingsitebackend.weddingsitebackend.service.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@RequestMapping("/api/admin/update")
public class AdminUpdateObjectsController {

    final AboutUsService aboutUsService;

    final DressCodeService dressCodeService;

    final InvitaitionTextService invitaitionTextService;

    final PersonalInvitationService personalInvitationService;

    final PlaceService placeService;

    final ProgramService programService;

    final StoryService storyService;

    final WeddingDateService weddingDateService;
    final ProgramsPartService programsPartService;

    public AdminUpdateObjectsController(AboutUsService aboutUsService, DressCodeService dressCodeService, InvitaitionTextService invitaitionTextService, PersonalInvitationService personalInvitationService, PlaceService placeService, ProgramService programService, StoryService storyService, WeddingDateService weddingDateService, ProgramsPartService programsPartService) {
        this.aboutUsService = aboutUsService;
        this.dressCodeService = dressCodeService;
        this.invitaitionTextService = invitaitionTextService;
        this.personalInvitationService = personalInvitationService;
        this.placeService = placeService;
        this.programService = programService;
        this.storyService = storyService;
        this.weddingDateService = weddingDateService;
        this.programsPartService = programsPartService;
    }

    @PostMapping("/aboutUs")
    public ResponseEntity<?> updateAboutUs(@Valid @RequestBody AboutUsRequest aboutUsRequest) {
        return aboutUsService.update(aboutUsRequest);
    }

    @PostMapping("/dressCode")
    public ResponseEntity<?> updateDressCode(@Valid @RequestBody DressCodeRequest dressCodeRequest) {
        return dressCodeService.update(dressCodeRequest);
    }

    @PostMapping("/invitationText")
    public ResponseEntity<?> updateInvitationText(@Valid @RequestBody InvitationTextRequest invitationTextRequest) {
        return invitaitionTextService.update(invitationTextRequest);
    }

    @PostMapping("/personalInvitation")
    public ResponseEntity<?> updatePersonalInvitation(@Valid @RequestBody PersonalInvitationRequest personalInvitationRequest) {
        return personalInvitationService.update(personalInvitationRequest);
    }

    @PostMapping("/place")
    public ResponseEntity<?> updatePlace(@Valid @RequestBody PlaceRequest placeRequest) {
        return placeService.update(placeRequest);
    }

    @PostMapping("/story")
    public ResponseEntity<?> updateStory(@Valid @RequestBody StoryRequest storyRequest) {
        return storyService.update(storyRequest);
    }

    @PostMapping("/weddingDate")
    public ResponseEntity<?> updateStory(@Valid @RequestBody WeddingDateRequest weddingDateRequest) {
        return weddingDateService.update(weddingDateRequest);
    }

    @PostMapping("/programsPart")
    public ResponseEntity<?> updateProgramsPart(@Valid @RequestBody ProgramsPartRequest programsPartRequest) {
        return programsPartService.update(programsPartRequest);
    }
    @PostMapping(value = "/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadPhoto(@RequestPart("file") @Valid @NotNull @NotBlank @RequestParam MultipartFile file,
                                              @RequestPart ("properties") @Valid AboutUsRequest aboutUsRequest) throws IOException {
        return aboutUsService.photoUpload(file,aboutUsRequest);
    }
    @PostMapping(value = "/photoFemale", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadDressFemale(@RequestParam MultipartFile file) throws IOException {
        return dressCodeService.photoUploadDressFemale(file);
    }
    @PostMapping(value = "/photoMale", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadDressMale(@RequestParam MultipartFile file) throws IOException {
        return dressCodeService.photoUploadDressMale(file);
    }
}

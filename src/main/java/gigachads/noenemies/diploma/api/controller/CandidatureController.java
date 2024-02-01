package gigachads.noenemies.diploma.api.controller;


import gigachads.noenemies.diploma.api.dto.CandidaturePlanResponse;
import gigachads.noenemies.diploma.api.dto.CandidaturePlanUpdate;
import gigachads.noenemies.diploma.api.dto.UserResponse;
import gigachads.noenemies.diploma.domain.mapper.UserMapper;
import gigachads.noenemies.diploma.domain.model.CandidaturePlan;
import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.service.CandidatureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/candidature")
@RequiredArgsConstructor
public class CandidatureController {
    private final UserMapper userMapper;
    private final CandidatureService candidatureService;


    @Operation(summary = "Apply for a candidature",
            operationId = "applyForCandidature",
            tags = {"Candidature"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully applied for candidature",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))})
            })
    @PostMapping("/apply")
    public String applyForCandidature(Principal principal) {
        UserId studentId = UserId.of(principal.getName());
        System.out.println(studentId);

        candidatureService.applyForCandidature(studentId);
        return "Application was sent";
    }

    @Operation(summary = "Approve candidature",
            operationId = "approveCandidature",
            tags = {"Candidature"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully approved candidature",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))})
            })
    @PostMapping("/approve/{studentId}")
    public String approveCandidature(Principal principal, UserId studentId) {
        UserId officialId = UserId.of(principal.getName());

        candidatureService.approveCandidature(studentId, officialId);
        return "Application was sent";
    }

    @Operation(summary = "Update candidature plan",
            operationId = "updateCandidaturePlan",
            tags = {"Candidature"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated candidature plan",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CandidaturePlanResponse.class))})
            })
    @PatchMapping("/plan")
    public String updateCandidaturePlan(Principal principal,
                                        @RequestBody CandidaturePlanUpdate update) {
        UserId studentId = UserId.of(principal.getName());

        candidatureService.updateCandidaturePlan(update, studentId);
        return "Application was sent";
    }

    @Operation(summary = "Approve candidature",
            operationId = "approveCandidature",
            tags = {"Candidature"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated candidature plan",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponse.class))})
            })
    @PostMapping("/plan/photo")
    public String uploadCandidatureImage(
            Principal principal,
            @RequestParam("photo") MultipartFile photo
    ) {
        String uploadDirectory = "src/main/resources/profile/pictures";
        String adsImagesString = "";

//        for (MultipartFile imageFile : adsImages) {
//            adsImagesString += candidatureService.saveImageToStorage(uploadDirectory, imageFile) + ",";

        // Save the adsImagesString in your database
        // You can also associate it with other data in your Ads object
        return "";
        }
}

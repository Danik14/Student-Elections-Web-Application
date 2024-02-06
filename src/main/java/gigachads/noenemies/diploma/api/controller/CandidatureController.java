package gigachads.noenemies.diploma.api.controller;


import gigachads.noenemies.diploma.api.dto.CandidaturePlanResponse;
import gigachads.noenemies.diploma.api.dto.CandidaturePlanUpdate;
import gigachads.noenemies.diploma.api.dto.CandidatureResponse;
import gigachads.noenemies.diploma.domain.mapper.CandidatureMapper;
import gigachads.noenemies.diploma.domain.mapper.UserMapper;
import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.service.CandidatureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/candidature")
@RequiredArgsConstructor
@Validated
public class CandidatureController {
    private final UserMapper userMapper;
    private final CandidatureService candidatureService;
    private final CandidatureMapper candidatureMapper;


    @Operation(summary = "Get active candidatures",
            operationId = "getActiveCandidatures",
            tags = {"Candidature"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully found active candidatures",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CandidatureResponse.class))})
            })
    @GetMapping("/active")
    @ResponseStatus(HttpStatus.OK)
    public List<CandidatureResponse> getActiveCandidatures(Principal principal) {
        return candidatureMapper.toResponse(candidatureService.findAllActiveCandidatures());
    }

    @Operation(summary = "Apply for a candidature",
            operationId = "applyForCandidature",
            tags = {"Candidature"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully applied for candidature",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))})
            })
    @PostMapping("/apply")
    @ResponseStatus(HttpStatus.OK)
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
    @ResponseStatus(HttpStatus.OK)
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
    @ResponseStatus(HttpStatus.OK)
    public String updateCandidaturePlan(Principal principal,
                                        @RequestBody CandidaturePlanUpdate update) {
        UserId studentId = UserId.of(principal.getName());

        candidatureService.updateCandidaturePlan(update, studentId);
        return "Application was sent";
    }
}

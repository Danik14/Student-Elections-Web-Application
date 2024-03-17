package gigachads.noenemies.diploma.api.controller;


import gigachads.noenemies.diploma.api.dto.CandidaturePlanResponse;
import gigachads.noenemies.diploma.api.dto.CandidaturePlanUpdate;
import gigachads.noenemies.diploma.api.dto.CandidatureResponse;
import gigachads.noenemies.diploma.api.dto.VoteResponse;
import gigachads.noenemies.diploma.domain.mapper.CandidatureMapper;
import gigachads.noenemies.diploma.domain.mapper.UserMapper;
import gigachads.noenemies.diploma.domain.mapper.VoteMapper;
import gigachads.noenemies.diploma.domain.model.CandidatureStageId;
import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.service.CandidatureService;
import gigachads.noenemies.diploma.domain.service.VoteService;
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

import static gigachads.noenemies.diploma.HelperClass.getUserIdByOauth2Principal;

@RestController
@RequestMapping("/api/v1/candidature")
@RequiredArgsConstructor
@Validated
public class CandidatureController {
    private final CandidatureService candidatureService;
    private final VoteService voteService;
    private final CandidatureMapper candidatureMapper;
    private final UserMapper userMapper;
    private final VoteMapper voteMapper;


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
    public void applyForCandidature(Principal principal) {
        candidatureService.applyForCandidature(getUserIdByOauth2Principal(principal));
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
    public CandidatureResponse approveCandidature(Principal principal,
                                          @PathVariable UserId studentId) {
        return candidatureMapper.toResponse(candidatureService.approveCandidature(studentId, getUserIdByOauth2Principal(principal)));
    }

    @Operation(summary = "Vote for candidature on current stage",
            operationId = "voteForCandidatureStage",
            tags = {"CandidatureStage"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully voted for candidature on current stage",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = VoteResponse.class))})
            })
    @GetMapping("/{candidatureStageId}/vote")
    @ResponseStatus(HttpStatus.OK)
    public VoteResponse voteForCandidatureStage(Principal principal,
                                                @PathVariable CandidatureStageId candidatureStageId) {
        return voteMapper.toResponse(
                voteService.voteForCandidatureStage(getUserIdByOauth2Principal(principal), candidatureStageId)
        );
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
    public CandidaturePlanResponse updateCandidaturePlan(Principal principal,
                                        @RequestBody CandidaturePlanUpdate update) {
        UserId studentId = UserId.of(principal.getName());

        return candidatureMapper.toCandidaturePlanResponse(candidatureService.updateCandidaturePlan(update, studentId));
    }
}

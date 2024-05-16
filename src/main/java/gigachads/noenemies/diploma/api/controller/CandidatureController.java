package gigachads.noenemies.diploma.api.controller;


import gigachads.noenemies.diploma.api.dto.CandidaturePlanResponse;
import gigachads.noenemies.diploma.api.dto.CandidaturePlanUpdate;
import gigachads.noenemies.diploma.api.dto.CandidatureResponse;
import gigachads.noenemies.diploma.domain.mapper.CandidatureMapper;
import gigachads.noenemies.diploma.domain.model.CandidatureId;
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

import static gigachads.noenemies.diploma.HelperClass.getUserIdByOauth2Principal;

@RestController
@RequestMapping("/api/v1/candidature")
@RequiredArgsConstructor
@Validated
public class CandidatureController {
    private final CandidatureService candidatureService;
    private final CandidatureMapper candidatureMapper;

    @Operation(summary = "Get candidature by user id",
            operationId = "getCandidatureByUserId",
            tags = {"Candidature"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully found candidature",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CandidatureResponse.class))})
            })
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    // TODO: IT
    public CandidatureResponse getCandidatureByUserId(Principal principal,
                                                  @PathVariable UserId userId) {
        return candidatureMapper.toResponse(candidatureService.findCandidatureByUserId(userId));
    }

    @Operation(summary = "Get candidature by id",
            operationId = "getCandidatureById",
            tags = {"Candidature"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully found candidature",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CandidatureResponse.class))})
            })
    @GetMapping("/{candidatureId}")
    @ResponseStatus(HttpStatus.OK)
    // TODO: IT
    public CandidatureResponse getCandidatureById(Principal principal,
                                                      @PathVariable CandidatureId candidatureId) {
        return candidatureMapper.toResponse(candidatureService.findCandidatureById(candidatureId));
    }

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
                                    schema = @Schema)})
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
                                    schema = @Schema(implementation = CandidatureResponse.class))})
            })
    @PostMapping("/approve/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public CandidatureResponse approveCandidature(Principal principal,
                                          @PathVariable UserId studentId) {
        return candidatureMapper.toResponse(candidatureService.approveCandidature(studentId, getUserIdByOauth2Principal(principal)));
    }

    @Operation(summary = "Get candidature plan",
            operationId = "getCandidaturePlanByCandidatureId",
            tags = {"Candidature"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully found candidature plan",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CandidaturePlanResponse.class))})
            })
    @GetMapping("/{candidatureId}/plan")
    @ResponseStatus(HttpStatus.OK)
    // TODO: IT
    public CandidaturePlanResponse getCandidaturePlanByCandidatureId(@PathVariable CandidatureId candidatureId) {
        return candidatureMapper.toCandidaturePlanResponse(candidatureService.findCandidaturePlanByCandidatureId(candidatureId));
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
    // TODO: IT
    public CandidaturePlanResponse updateCandidaturePlan(Principal principal,
                                        @RequestBody CandidaturePlanUpdate update) {
        UserId candidateId = UserId.of(principal.getName());
        return candidatureMapper.toCandidaturePlanResponse(candidatureService.updateCandidaturePlan(update, candidateId));
    }

    @Operation(summary = "Deactivate candidature by id",
            operationId = "deactivateCandidature",
            tags = {"Candidature"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully deactivated candidature",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CandidatureResponse.class))})
            })
    @PatchMapping("/{candidatureId}/deactivate")
    @ResponseStatus(HttpStatus.OK)
    // TODO: IT
    public CandidatureResponse deactivateCandidature(
            @PathVariable CandidatureId candidatureId) {
        return candidatureMapper.toResponse(candidatureService.deactivateCandidature(candidatureId));
    }
}

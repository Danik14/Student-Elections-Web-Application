package gigachads.noenemies.diploma.api.controller;


import gigachads.noenemies.diploma.api.dto.CandidaturePlanResponse;
import gigachads.noenemies.diploma.api.dto.CandidatureStageResponse;
import gigachads.noenemies.diploma.api.dto.VoteResponse;
import gigachads.noenemies.diploma.domain.mapper.CandidatureMapper;
import gigachads.noenemies.diploma.domain.mapper.UserMapper;
import gigachads.noenemies.diploma.domain.mapper.VoteMapper;
import gigachads.noenemies.diploma.domain.model.CandidatureStageId;
import gigachads.noenemies.diploma.domain.model.ElectionId;
import gigachads.noenemies.diploma.domain.model.StageStatus;
import gigachads.noenemies.diploma.domain.service.CandidatureStageService;
import gigachads.noenemies.diploma.domain.service.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static gigachads.noenemies.diploma.HelperClass.getUserIdByOauth2Principal;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CandidatureStageController {
    private final CandidatureStageService candidatureStageService;
    private final VoteService voteService;
    private final CandidatureMapper candidatureMapper;
    private final VoteMapper voteMapper;


    @Operation(summary = "Get current election's candidature stage info by id",
            operationId = "getCurrentElectionCandidatureStageById",
            tags = {"CandidatureStage"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully found candidature stage",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CandidatureStageResponse.class))})
            })
    @GetMapping("/candidature-stage/{candidatureStageId}")
    @ResponseStatus(HttpStatus.OK)
    public CandidatureStageResponse getCandidatureStageById(
            @PathVariable CandidatureStageId candidatureStageId
    ) {
        return candidatureMapper
                .toCandidatureStageResponse(candidatureStageService.findCandidatureStageById(candidatureStageId));
    }

    @Operation(summary = "Get candidature stages info by election id",
            operationId = "getCandidatureStagesByElectionId",
            tags = {"CandidatureStage"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully found candidature stages",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CandidatureStageResponse.class))})
            })
    @GetMapping("/election/{electionId}/candidature-stage")
    @ResponseStatus(HttpStatus.OK)
    public List<CandidatureStageResponse> getCandidatureStagesByElectionId(@PathVariable ElectionId electionId) {
        return candidatureMapper
                .toCandidatureStageResponse(candidatureStageService.findCandidatureStagesByElectionId(electionId));
    }

    @Operation(summary = "Get current candidature stages",
            operationId = "getCurrentElectionCurrentCandidatureStages",
            tags = {"CandidatureStage"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully found candidature stages",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CandidatureStageResponse.class))})
            })
    @GetMapping("election/current/candidature-stage/current")
    @ResponseStatus(HttpStatus.OK)
    public List<CandidatureStageResponse> getCurrentElectionCurrentCandidatureStages() {
        return candidatureMapper
                .toCandidatureStageResponse(candidatureStageService.findCurrentElectionCandidatureStagesByStatus(StageStatus.IN_PROGRESS));
    }

//    @Operation(summary = "Get candidature stage info by candidatureStageId",
//            operationId = "getCurrentElectionCurrentCandidatureStages",
//            tags = {"CandidatureStage"},
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "Successfully found candidature stages",
//                            content = {@Content(mediaType = "application/json",
//                                    schema = @Schema(implementation = CandidatureStageResponse.class))})
//            })
//    @GetMapping("/candidature-stage/{candidatureStageId}/info")
//    @ResponseStatus(HttpStatus.OK)
//    public CandidaturePlanResponse getCandidatureStageInfoByCandidatureStageId(@PathVariable CandidatureStageId candidatureStageId) {
//        return null;
//    }

    @Operation(summary = "Vote for candidature on current stage",
            operationId = "voteForCandidatureStage",
            tags = {"CandidatureStage"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully voted for candidature on current stage",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = VoteResponse.class))})
            })
    @GetMapping("/candidature-stage/{candidatureStageId}/vote")
    @ResponseStatus(HttpStatus.OK)
    // TODO: IT
    public VoteResponse voteForCandidatureStage(Principal principal,
                                                @PathVariable CandidatureStageId candidatureStageId) {
        return voteMapper.toResponse(
                voteService.voteForCandidatureStage(getUserIdByOauth2Principal(principal), candidatureStageId)
        );
    }
}

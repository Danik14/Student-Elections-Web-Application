package gigachads.noenemies.diploma.api.controller;

import gigachads.noenemies.diploma.api.dto.CandidatureStageResponse;
import gigachads.noenemies.diploma.api.dto.ElectionCreate;
import gigachads.noenemies.diploma.api.dto.ElectionResponse;
import gigachads.noenemies.diploma.domain.mapper.CandidatureMapper;
import gigachads.noenemies.diploma.domain.mapper.ElectionMapper;
import gigachads.noenemies.diploma.domain.model.CandidatureStageId;
import gigachads.noenemies.diploma.domain.model.ElectionId;
import gigachads.noenemies.diploma.domain.model.StageStatus;
import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.service.CandidatureStageService;
import gigachads.noenemies.diploma.domain.service.ElectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/election")
@RequiredArgsConstructor
public class ElectionController {
    private final ElectionService electionService;
    private final CandidatureStageService candidatureStageService;
    private final ElectionMapper electionMapper;
    private final CandidatureMapper candidatureMapper;

    @Operation(summary = "Get elections",
            operationId = "getElections",
            tags = {"Election"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of all elections",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ElectionResponse.class))})
            })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ElectionResponse> getElections(@RequestParam(value = "limit",
                                                        required = false,
                                                        defaultValue = "10") Integer limit) {
        return electionMapper.toResponse(electionService.getElections(limit));
    }

    @Operation(summary = "Get current",
            operationId = "getCurrentElection",
            tags = {"Election"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Found election",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ElectionResponse.class))})
            })
    @GetMapping("/current")
    @ResponseStatus(HttpStatus.OK)
    public ElectionResponse getCurrentElection() {
        return electionMapper.toResponse(electionService.getCurrentElection());
    }

//    @Operation(summary = "Get election by Id",
//            operationId = "getElectionById",
//            tags = {"Election"},
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "Found election",
//                            content = {@Content(mediaType = "application/json",
//                                    schema = @Schema(implementation = ElectionResponse.class))})
//            })
//    @GetMapping("/{electionId}")
//    @ResponseStatus(HttpStatus.OK)
//    public ElectionResponse getElectionById(@PathVariable ElectionId electionId) {
//        return electionMapper.toResponse(electionService.getElectionById(electionId));
//    }

    @Operation(summary = "Get candidature stages info by election id",
            operationId = "getCandidatureStagesByElectionId",
            tags = {"CandidatureStage"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully found candidature stages",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CandidatureStageResponse.class))})
            })
    @GetMapping("/{electionId}/candidature-stage")
    @ResponseStatus(HttpStatus.OK)
    public List<CandidatureStageResponse> getCandidatureStagesByElectionId(@PathVariable ElectionId electionId) {
        return candidatureMapper
                .toCandidatureStageResponse(candidatureStageService.findCandidatureStagesByElectionId(electionId));
    }

    @GetMapping("/{electionId}")
    @ResponseStatus(HttpStatus.OK)
    public ElectionResponse getElectionById(@PathVariable ElectionId electionId) {
        return electionMapper.toResponse(electionService.getElectionById(electionId));
    }

    @Operation(summary = "Get current candidature stages info",
            operationId = "getCurrentElectionCurrentCandidatureStages",
            tags = {"CandidatureStage"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully found candidature stages",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CandidatureStageResponse.class))})
            })
    @GetMapping("/current/candidature-stage/current")
    @ResponseStatus(HttpStatus.OK)
    public List<CandidatureStageResponse> getCurrentElectionCurrentCandidatureStages() {
        return candidatureMapper
                .toCandidatureStageResponse(candidatureStageService.findCurrentElectionCandidatureStagesByStatus(StageStatus.IN_PROGRESS));
    }

    @Operation(summary = "Get current election's candidature stage info by id",
            operationId = "getCurrentElectionCandidatureStageById",
            tags = {"CandidatureStage"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully found candidature stage",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CandidatureStageResponse.class))})
            })
    @GetMapping("/current/candidature-stage/{candidatureStageId}")
    @ResponseStatus(HttpStatus.OK)
    public CandidatureStageResponse getCurrentElectionCandidatureStageById(
            @PathVariable CandidatureStageId candidatureStageId
            ) {
        return candidatureMapper
                .toCandidatureStageResponse(candidatureStageService.findCandidatureStageById(candidatureStageId));
    }

    @Operation(summary = "Create a new Election",
            operationId = "createNewElection",
            tags = {"Election"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Election created successfully",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ElectionResponse.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid request body",
                            content = @Content)})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ElectionResponse createNewElection(
                                           @RequestBody ElectionCreate request) {
        return electionMapper.toResponse(electionService.createElection(request));
    }

    @Operation(summary = "Initiate election by Id",
            operationId = "initiateElectionById",
            tags = {"CandidatureStage"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully initiated election",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ElectionResponse.class))})
            })
    @PutMapping("/{electionId}/initiate")
    @ResponseStatus(HttpStatus.OK)
    public List<CandidatureStageResponse> initiateElectionById(Principal principal, @PathVariable ElectionId electionId) {
        return candidatureMapper.toCandidatureStageResponse(electionService.initiateElection(getUserIdByOauth2Principal(principal), electionId));
    }

    private UserId getUserIdByOauth2Principal(Principal principal){
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
        return UserId.of((String) token.getPrincipal().getAttribute("oid"));
    }
}

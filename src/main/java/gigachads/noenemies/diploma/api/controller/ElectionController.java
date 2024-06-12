package gigachads.noenemies.diploma.api.controller;

import com.azure.core.annotation.Delete;
import gigachads.noenemies.diploma.api.dto.CandidatureStageResponse;
import gigachads.noenemies.diploma.api.dto.ElectionCreate;
import gigachads.noenemies.diploma.api.dto.ElectionResponse;
import gigachads.noenemies.diploma.api.dto.ElectionUpdate;
import gigachads.noenemies.diploma.domain.mapper.CandidatureMapper;
import gigachads.noenemies.diploma.domain.mapper.ElectionMapper;
import gigachads.noenemies.diploma.domain.model.ElectionId;
import gigachads.noenemies.diploma.domain.service.ElectionService;
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
@RequestMapping("/api/v1/election")
@RequiredArgsConstructor
public class ElectionController {
    private final ElectionService electionService;
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

    @GetMapping("/{electionId}")
    @ResponseStatus(HttpStatus.OK)
    public ElectionResponse getElectionById(@PathVariable("electionId") ElectionId electionId) {
        return electionMapper.toResponse(electionService.getElectionById(electionId));
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

    @Operation(summary = "Update Election",
            operationId = "updateElection",
            tags = {"Election"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Election updated successfully",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ElectionResponse.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid request body",
                            content = @Content)})
    @PutMapping("/{electionId}")
    @ResponseStatus(HttpStatus.OK)
    public ElectionResponse updateElection(
            @PathVariable ElectionId electionId,
            @RequestBody ElectionUpdate update) {
        return electionMapper.toResponse(electionService.updateElection(electionId, update));
    }

    @Operation(summary = "Initiate election by Id",
            operationId = "initiateElectionById",
            tags = {"Election"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully initiated election",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ElectionResponse.class))})
            })
    @PutMapping("/{electionId}/initiate")
    @ResponseStatus(HttpStatus.OK)
    // TODO: IT
    public List<CandidatureStageResponse> initiateElectionById(
            Principal principal,
            @PathVariable ElectionId electionId) {
        return candidatureMapper.toCandidatureStageResponse(electionService.initiateElection(getUserIdByOauth2Principal(principal), electionId));
    }

    @Operation(summary = "Finish election by Id",
            operationId = "finishElectionById",
            tags = {"Election"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully finished election",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ElectionResponse.class))})
            })
    @PutMapping("/{electionId}/finish")
    @ResponseStatus(HttpStatus.OK)
    // TODO: IT
    public ElectionResponse finishElectionById(
            @PathVariable ElectionId electionId) {
        return electionMapper.toResponse(electionService.finishElectionById(electionId));
    }

    @Operation(summary = "Delete election by Id",
            operationId = "deleteElectionById",
            tags = {"Election"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "Successfully deleted election",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ElectionResponse.class))})
            })
    @DeleteMapping("/{electionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    // TODO: IT
    public void deleteElectionById(
            @PathVariable ElectionId electionId) {
        electionService.deleteElectionById(electionId);
    }
}

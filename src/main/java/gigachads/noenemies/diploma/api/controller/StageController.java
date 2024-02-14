package gigachads.noenemies.diploma.api.controller;

import gigachads.noenemies.diploma.api.dto.ElectionResponse;
import gigachads.noenemies.diploma.api.dto.StageCreate;
import gigachads.noenemies.diploma.api.dto.StageResponse;
import gigachads.noenemies.diploma.domain.mapper.StageMapper;
import gigachads.noenemies.diploma.domain.model.ElectionId;
import gigachads.noenemies.diploma.domain.service.StageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/election/{electionId}/stage")
@RequiredArgsConstructor
public class StageController {
    private final StageService stageService;
    private final StageMapper stageMapper;

    @Operation(summary = "Get stages by election id",
            operationId = "getStagesByElectionId",
            tags = {"Stage"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of election's stages",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = StageResponse.class))})
            })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StageResponse> getStagesByElectionId(@PathVariable ElectionId electionId) {
        return stageMapper.toResponse(stageService.findStagesByElectionId(electionId));
    }

    @Operation(summary = "Get stages by election id",
            operationId = "getStagesByElectionId",
            tags = {"Stage"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Current Stage",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = StageResponse.class))})
            })
    @GetMapping("/current")
    @ResponseStatus(HttpStatus.OK)
    public StageResponse getCurrentElectionStage(@PathVariable ElectionId electionId) {
        return stageMapper.toResponse(stageService.findCurrentStageByElectionId(electionId));
    }

    @Operation(summary = "Create new election's stage",
            operationId = "createElectionStage",
            tags = {"Stage"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Create new election stage",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = StageResponse.class))})
            })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StageResponse createElectionStage(
            @PathVariable ElectionId electionId,
            @RequestBody StageCreate create) {
        return stageMapper.toResponse(stageService.createElectionStage(electionId, create));
    }
}

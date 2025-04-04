package gigachads.noenemies.diploma.api.controller;

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
@RequestMapping("/api/v1/election/")
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
    @GetMapping("{electionId}/stage")
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
    @GetMapping("/{electionId}/stage/current")
    @ResponseStatus(HttpStatus.OK)
    public StageResponse getCurrentElectionStage(@PathVariable ElectionId electionId) {
        return stageMapper.toResponse(stageService.findCurrentStageByElectionId(electionId));
    }

    @Operation(summary = "Get current election's current stage",
            operationId = "getCurrentElectionCurrentStage",
            tags = {"Stage"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Current Election's current stage",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = StageResponse.class))})
            })
    @GetMapping("/current/stage/current")
    @ResponseStatus(HttpStatus.OK)
    public StageResponse getCurrentElectionCurrentStage() {
        return stageMapper.toResponse(stageService.findCurrentElectionCurrentStage());
    }

    @Operation(summary = "Create new election's stage",
            operationId = "createElectionStage",
            tags = {"Stage"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Create new election stage",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = StageResponse.class))})
            })
    @PostMapping("/{electionId}/stage")
    @ResponseStatus(HttpStatus.CREATED)
    public StageResponse createElectionStage(
            @PathVariable ElectionId electionId,
            @RequestBody StageCreate create) {
        return stageMapper.toResponse(stageService.createElectionStage(electionId, create));
    }

//    @Operation(summary = "Create new election's stage",
//            operationId = "createElectionStage",
//            tags = {"Stage"},
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "Create new election stage",
//                            content = {@Content(mediaType = "application/json",
//                                    schema = @Schema(implementation = StageResponse.class))})
//            })
//    @PutMapping("/{electionId}/stage")
//    @ResponseStatus(HttpStatus.CREATED)
//    public StageResponse createElectionStage(
//            @PathVariable ElectionId electionId,
//            @RequestBody StageCreate create) {
//        return stageMapper.toResponse(stageService.createElectionStage(electionId, create));
//    }
}

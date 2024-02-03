package gigachads.noenemies.diploma.api.controller;

import com.azure.core.annotation.Patch;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.QueryParam;
import gigachads.noenemies.diploma.api.dto.ElectionCreateRequest;
import gigachads.noenemies.diploma.api.dto.ElectionResponse;
import gigachads.noenemies.diploma.api.dto.UserResponse;
import gigachads.noenemies.diploma.domain.mapper.ElectionMapper;
import gigachads.noenemies.diploma.domain.model.ElectionId;
import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.service.ElectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/election")
@RequiredArgsConstructor
public class ElectionController {
    private final ElectionService electionService;
    private final ElectionMapper electionMapper;

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

    @Operation(summary = "Get election by Id",
            operationId = "getElectionById",
            tags = {"Election"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Found election",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ElectionResponse.class))})
            })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ElectionResponse> getElectionById(@PathVariable ElectionId electionId) {
        return ResponseEntity.ok(electionMapper.toResponse(electionService.getElectionById(electionId)));
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
    public ElectionResponse createNewElection(@AuthenticationPrincipal UserDetails userDetails,
                                           @RequestBody ElectionCreateRequest request) {
        System.out.println("*****");
        System.out.println(userDetails);
        return electionMapper.toResponse(electionService.createElection(request));
    }

    @Operation(summary = "Initiate election by Id",
            operationId = "initiateElectionById",
            tags = {"Election"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully initiated election",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ElectionResponse.class))})
            })
    @Put("/{id}/initiate")
    @ResponseStatus(HttpStatus.OK)
    public void initiateElectionById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable ElectionId electionId) {
//        UserId officialId = userDetails.getUsername();
        System.out.println(userDetails);
        electionService.initiateElection(UserId.of(UUID.randomUUID()), electionId);
    }
}

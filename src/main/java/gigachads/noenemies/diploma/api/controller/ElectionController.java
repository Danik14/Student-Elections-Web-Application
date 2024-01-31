package gigachads.noenemies.diploma.api.controller;

import com.azure.core.annotation.Patch;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.QueryParam;
import gigachads.noenemies.diploma.api.dto.ElectionCreateRequest;
import gigachads.noenemies.diploma.api.dto.ElectionResponse;
import gigachads.noenemies.diploma.domain.mapper.ElectionMapper;
import gigachads.noenemies.diploma.domain.model.ElectionId;
import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.service.ElectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/election")
@RequiredArgsConstructor
public class ElectionController {
    private final ElectionService electionService;
    private final ElectionMapper electionMapper;

//    @GetMapping
//    public ResponseEntity<List<ElectionResponse>> getAllElections(@PathVariable ElectionId electionId) {
//        return electionMapper.toResponse(electionService.getElectionById(electionId));
//    }

    @GetMapping
    public List<ElectionResponse> getElections(@RequestParam(value = "limit",
                                                        required = false,
                                                        defaultValue = "10") Integer limit) {
        return electionMapper.toResponse(electionService.getElections(limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElectionResponse> getElectionById(@PathVariable ElectionId electionId) {
        return ResponseEntity.ok(electionMapper.toResponse(electionService.getElectionById(electionId)));
    }

    @PostMapping
    public ElectionResponse createElection(@RequestBody ElectionCreateRequest request) {
        return electionMapper.toResponse(electionService.createElection(request));
    }

    @Put("/{id}/initiate")
    public void initiateElectionById(Principal principal, @PathVariable ElectionId electionId) {
        UserId officialId = UserId.of(principal.getName());
        electionService.initiateElection(officialId, electionId);
    }
}

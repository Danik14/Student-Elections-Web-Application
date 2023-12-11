package gigachads.noenemies.diploma.api.controller;

import gigachads.noenemies.diploma.api.dto.election.ElectionResponse;
import gigachads.noenemies.diploma.domain.mapper.ElectionMapper;
import gigachads.noenemies.diploma.domain.model.election.ElectionId;
import gigachads.noenemies.diploma.domain.service.ElectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id}")
    public ResponseEntity<ElectionResponse> getElectionById(@PathVariable ElectionId electionId) {
        return ResponseEntity.ok(electionMapper.toResponse(electionService.getElectionById(electionId)));
    }

//    @PostMapping
//    public ElectionResponse createElection(@RequestBody ElectionCreateRequest request) {
//        return electionMapper.toResponse(electionService.getElectionById(electionId));
//    }

//    @Patch("/{id}")
//    public ElectionResponse initiateElectionById(@PathVariable ElectionId electionId) {
//        return electionMapper.toResponse(electionService.getElectionById(electionId));
//    }
}

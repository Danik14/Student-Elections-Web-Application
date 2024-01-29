package gigachads.noenemies.diploma.domain.service.impl;

import gigachads.noenemies.diploma.domain.exception.ElectionNotFoundException;
import gigachads.noenemies.diploma.domain.mapper.ElectionMapper;
import gigachads.noenemies.diploma.domain.model.Election;
import gigachads.noenemies.diploma.domain.model.ElectionId;
import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.service.ElectionService;
import gigachads.noenemies.diploma.storage.jpa.repository.ElectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElectionServiceImpl implements ElectionService {
    private final ElectionRepository electionRepository;
    private final ElectionMapper electionMapper;

    @Override
    public Election createElection(Election election) {
       return  electionMapper.toDomain(electionRepository.save(electionMapper.toEntity(election)));
    }

    @Override
    public Election getElectionById(ElectionId electionId) {
        return electionMapper.toDomain(electionRepository.findById(electionId.getId())
                .orElseThrow(() ->
                        new ElectionNotFoundException("election not found with id: " + electionId.getAsString()
                        )
                )
        );
    }

    @Override
    public void initiateElection(UserId officialId, ElectionId electionId) {

    }
}

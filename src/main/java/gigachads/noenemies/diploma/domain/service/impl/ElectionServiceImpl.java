package gigachads.noenemies.diploma.domain.service.impl;

import gigachads.noenemies.diploma.api.dto.ElectionCreateRequest;
import gigachads.noenemies.diploma.domain.mapper.ElectionMapper;
import gigachads.noenemies.diploma.domain.model.*;
import gigachads.noenemies.diploma.domain.service.ElectionService;
import gigachads.noenemies.diploma.exception.EntityNotFoundException;
import gigachads.noenemies.diploma.exception.EntityNotUpdatedException;
import gigachads.noenemies.diploma.storage.jpa.entity.ElectionEntity;
import gigachads.noenemies.diploma.storage.jpa.repository.ElectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ElectionServiceImpl implements ElectionService {
    private final ElectionRepository electionRepository;
    private final ElectionMapper electionMapper;

    @Override
    public Election createElection(ElectionCreateRequest create) {
       return electionMapper.toDomain(electionRepository.save(electionMapper.toEntity(create)));
    }

    @Override
    public List<Election> getElections(Integer limit) {
        return electionMapper.toDomain(electionRepository.findTopNByOrderByCreatedAtDesc(limit));
    }

    @Override
    public Election getElectionById(ElectionId id) {
        return electionMapper.toDomain(getElectionEntityById(id));
    }

    @Override
    @Transactional
    public void initiateElection(UserId officialId, ElectionId electionId) {
        ElectionEntity entity = getElectionEntityById(electionId);
        if (electionRepository.initiateElection(electionId.getId(), ElectionStatus.IN_PROGRESS) == 0) {
            throw new EntityNotUpdatedException("Failed to initiate Election Entity: " + entity);
        }
    }

    private ElectionEntity getElectionEntityById(ElectionId id) {
        return electionRepository.findById(id.getId())
                .orElseThrow(() ->
                        new EntityNotUpdatedException("Election not found with id: " + id.getAsString()
                        )
                );
    }
}

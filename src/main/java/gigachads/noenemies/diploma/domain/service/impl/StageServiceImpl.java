package gigachads.noenemies.diploma.domain.service.impl;

import gigachads.noenemies.diploma.api.dto.StageCreate;
import gigachads.noenemies.diploma.domain.mapper.StageMapper;
import gigachads.noenemies.diploma.domain.model.*;
import gigachads.noenemies.diploma.domain.service.StageService;
import gigachads.noenemies.diploma.exception.EntityNotFoundException;
import gigachads.noenemies.diploma.storage.jpa.entity.ElectionEntity;
import gigachads.noenemies.diploma.storage.jpa.entity.StageEntity;
import gigachads.noenemies.diploma.storage.jpa.repository.ElectionRepository;
import gigachads.noenemies.diploma.storage.jpa.repository.StageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class StageServiceImpl implements StageService {
    private final StageRepository stageRepository;
    private final ElectionRepository electionRepository;
    private final StageMapper stageMapper;

    @Override
    public List<Stage> findStagesByElectionId(ElectionId electionId) {
        return stageMapper.toDomain(stageRepository.findByElection_Id(electionId.getId()));
    }

    @Override
    public Stage createElectionStage(ElectionId electionId, StageCreate create) {
        ElectionEntity electionEntity = electionRepository.findById(electionId.getId())
                .orElseThrow(() -> new EntityNotFoundException("Election not found with id: " + electionId));

        StageEntity stageEntity = stageMapper.toEntity(create);
        stageEntity.setElection(electionEntity);
        stageEntity = stageRepository.save(stageEntity);

        return stageMapper.toDomain(stageEntity);
    }

    @Override
    public Stage findStageById(StageId stageId) {
        return stageMapper.toDomain(findEntityById(stageId));
    }

    @Override
    public Stage findCurrentStageByElectionId(ElectionId electionId) {
        return stageMapper.toDomain(findByElectionIdAndStatus(electionId, StageStatus.IN_PROGRESS));
    }

    @Override
    public Stage findCurrentElectionCurrentStage() {
        return findCurrentStageByElectionId(ElectionId.of(findCurrentElectionEntity().getId()));
    }

    private ElectionEntity findCurrentElectionEntity() {
        return electionRepository.findInProgressElection().orElseThrow(
                () -> new EntityNotFoundException("No election found with status " + ElectionStatus.IN_PROGRESS)
        );
    }

    private StageEntity findByElectionIdAndStatus(ElectionId electionId, StageStatus status) {
        return stageRepository.findByElection_IdAndStatus(electionId.getId(), status).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("No Stage found with election id %s and status %s", electionId, status)
                )
        );
    }

    private StageEntity findEntityById(StageId stageId){
        return stageRepository.findById(stageId.getId())
                .orElseThrow(() -> new EntityNotFoundException("Stage not found with id: " + stageId));
    }
}

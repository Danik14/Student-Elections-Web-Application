package gigachads.noenemies.diploma.domain.service.impl;

import gigachads.noenemies.diploma.api.dto.StageCreate;
import gigachads.noenemies.diploma.domain.mapper.StageMapper;
import gigachads.noenemies.diploma.domain.model.ElectionId;
import gigachads.noenemies.diploma.domain.model.Stage;
import gigachads.noenemies.diploma.domain.model.StageId;
import gigachads.noenemies.diploma.domain.model.StageStatus;
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
    public Stage findCurrentStage() {
        return stageMapper.toDomain(findByStatus(StageStatus.IN_PROGRESS));
    }

    private StageEntity findByStatus(StageStatus status) {
        return stageRepository.findByStatus(status).orElseThrow(
                () -> new EntityNotFoundException("No stage found with status " + status)
        );
    }

    private StageEntity findEntityById(StageId stageId){
        return stageRepository.findById(stageId.getId())
                .orElseThrow(() -> new EntityNotFoundException("Stage not found with id: " + stageId));
    }
}

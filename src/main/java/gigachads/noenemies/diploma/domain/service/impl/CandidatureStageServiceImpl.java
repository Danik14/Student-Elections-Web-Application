package gigachads.noenemies.diploma.domain.service.impl;

import gigachads.noenemies.diploma.domain.mapper.CandidatureMapper;
import gigachads.noenemies.diploma.domain.model.CandidatureStage;
import gigachads.noenemies.diploma.domain.model.ElectionId;
import gigachads.noenemies.diploma.domain.model.ElectionStatus;
import gigachads.noenemies.diploma.domain.model.StageStatus;
import gigachads.noenemies.diploma.domain.service.CandidatureStageService;
import gigachads.noenemies.diploma.exception.EntityNotFoundException;
import gigachads.noenemies.diploma.storage.jpa.entity.CandidatureStageEntity;
import gigachads.noenemies.diploma.storage.jpa.entity.ElectionEntity;
import gigachads.noenemies.diploma.storage.jpa.repository.CandidatureStageRepository;
import gigachads.noenemies.diploma.storage.jpa.repository.ElectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidatureStageServiceImpl implements CandidatureStageService {
    private final CandidatureStageRepository candidatureStageRepository;
    private final ElectionRepository electionRepository;
    private final CandidatureMapper candidatureMapper;

    @Override
    public List<CandidatureStage> findCandidatureStagesByElectionId(ElectionId electionId) {
        return candidatureMapper.toCandidatureStageDomain(
                candidatureStageRepository.findByElectionId(electionId.getId())
        );
    }

    @Override
    public List<CandidatureStage> findCandidatureStagesByElectionIdAndStatus(ElectionId electionId, StageStatus stageStatus) {
        return candidatureMapper.toCandidatureStageDomain(
                candidatureStageRepository.findByElectionIdAndStageStatus(electionId.getId(), stageStatus)
                        .stream()
                        .sorted(Comparator.comparingInt((CandidatureStageEntity candidatureStageEntity) ->
                                candidatureStageEntity.getVotes().size()).reversed())
                        .collect(Collectors.toList())
        );
    }

    @Override
    public List<CandidatureStage> findCurrentElectionCandidatureStagesByStatus(StageStatus stageStatus) {
        return candidatureMapper.toCandidatureStageDomain(
                candidatureStageRepository.findByElectionIdAndStageStatus(findCurrentElectionEntity().getId(), stageStatus)
                        .stream()
                        .sorted(Comparator.comparingInt((CandidatureStageEntity candidatureStageEntity) ->
                                candidatureStageEntity.getVotes().size()).reversed())
                        .collect(Collectors.toList())
        );
    }

    private ElectionEntity findCurrentElectionEntity() {
        return electionRepository.findInProgressElection().orElseThrow(
                () -> new EntityNotFoundException("No election found with status " + ElectionStatus.IN_PROGRESS)
        );
    }
}

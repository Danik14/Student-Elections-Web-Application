package gigachads.noenemies.diploma.domain.service.impl;

import gigachads.noenemies.diploma.domain.mapper.CandidatureMapper;
import gigachads.noenemies.diploma.domain.model.*;
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
        System.out.println(candidatureStageRepository.findByElectionIdAndStageStatus(findCurrentElectionEntity().getId(), stageStatus));
        return candidatureMapper.toCandidatureStageDomain(
                candidatureStageRepository.findByElectionIdAndStageStatus(findCurrentElectionEntity().getId(), stageStatus)
                        .stream()
                        .sorted(Comparator.comparingInt((CandidatureStageEntity candidatureStageEntity) ->
                                candidatureStageEntity.getVotes().size()).reversed())
                        .collect(Collectors.toList())
        );
    }

    @Override
    public CandidatureStage findCandidatureStageById(CandidatureStageId candidatureStageId) {
        return candidatureMapper.toCandidatureStageDomain(findCandidatureStageEntityById(candidatureStageId));
    }

    private CandidatureStageEntity findCandidatureStageEntityById(CandidatureStageId candidatureStageId){
        return candidatureStageRepository.findById(candidatureStageId.getId()).orElseThrow(
                () -> new EntityNotFoundException("CandidatureStage not found with id " + candidatureStageId)
        );
    }
    private ElectionEntity findCurrentElectionEntity() {
        return electionRepository.findInProgressElection().orElseThrow(
                () -> new EntityNotFoundException("No election found with status " + ElectionStatus.IN_PROGRESS)
        );
    }
}

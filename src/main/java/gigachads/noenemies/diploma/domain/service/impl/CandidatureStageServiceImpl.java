package gigachads.noenemies.diploma.domain.service.impl;

import gigachads.noenemies.diploma.api.dto.CandidatureStageInfoUpdate;
import gigachads.noenemies.diploma.domain.mapper.CandidatureMapper;
import gigachads.noenemies.diploma.domain.model.*;
import gigachads.noenemies.diploma.domain.service.CandidatureStageService;
import gigachads.noenemies.diploma.exception.EntityNotFoundException;
import gigachads.noenemies.diploma.storage.jpa.entity.CandidatureStageEntity;
import gigachads.noenemies.diploma.storage.jpa.entity.CandidatureStageInfoEntity;
import gigachads.noenemies.diploma.storage.jpa.entity.ElectionEntity;
import gigachads.noenemies.diploma.storage.jpa.repository.CandidatureStageInfoRepository;
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
    private final CandidatureStageInfoRepository candidatureStageInfoRepository;

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

    @Override
    public CandidatureStageInfo findCandidatureStageInfoById(CandidatureStageInfoId id) {
        return candidatureMapper.toCandidatureStageInfoDomain(findCandidatureStageInfoEntityById(id));
    }

    @Override
    public CandidatureStageInfo findInfoByCandidatureStageId(CandidatureStageId candidatureStageId) {
         return candidatureMapper.toCandidatureStageInfoDomain(findCandidatureStageInfoEntityByCandidatureStageId(candidatureStageId));
    }

    @Override
    public CandidatureStageInfo updateCandidatureStageInfo(CandidatureStageInfoId id, CandidatureStageInfoUpdate update) {
        var entity = findCandidatureStageInfoEntityById(id).toBuilder();

        if (update.getDescription() != null){
            entity.description(update.getDescription());
        }

        if (update.getLink1() != null){
            entity.link1(update.getLink1());
        }

        if (update.getLink2() != null){
            entity.link2(update.getLink2());
        }

        return candidatureMapper.toCandidatureStageInfoDomain(candidatureStageInfoRepository.save(entity.build()));
    }

    private CandidatureStageEntity findCandidatureStageEntityById(CandidatureStageId candidatureStageId){
        return candidatureStageRepository.findById(candidatureStageId.getId()).orElseThrow(
                () -> new EntityNotFoundException("CandidatureStage not found with id " + candidatureStageId)
        );
    }

    private CandidatureStageInfoEntity findCandidatureStageInfoEntityById(CandidatureStageInfoId candidatureStageInfoId){
        return candidatureStageInfoRepository.findById(candidatureStageInfoId.getId()).orElseThrow(
                () -> new EntityNotFoundException("CandidatureStageInfo not found with id " + candidatureStageInfoId)
        );
    }

    private CandidatureStageInfoEntity findCandidatureStageInfoEntityByCandidatureStageId(CandidatureStageId candidatureStageId){
        return candidatureStageInfoRepository.findByCandidatureStage_Id(candidatureStageId.getId()).orElseThrow(
                () -> new EntityNotFoundException("CandidatureStageInfo not found with candidatureId " + candidatureStageId)
        );
    }

    private ElectionEntity findCurrentElectionEntity() {
        return electionRepository.findInProgressElection().orElseThrow(
                () -> new EntityNotFoundException("No election found with status " + ElectionStatus.IN_PROGRESS)
        );
    }
}

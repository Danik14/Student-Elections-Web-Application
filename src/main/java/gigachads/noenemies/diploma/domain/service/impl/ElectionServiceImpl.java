package gigachads.noenemies.diploma.domain.service.impl;

import gigachads.noenemies.diploma.api.dto.ElectionCreate;
import gigachads.noenemies.diploma.api.dto.ElectionUpdate;
import gigachads.noenemies.diploma.domain.mapper.CandidatureMapper;
import gigachads.noenemies.diploma.domain.mapper.ElectionMapper;
import gigachads.noenemies.diploma.domain.model.*;
import gigachads.noenemies.diploma.domain.service.ElectionService;
import gigachads.noenemies.diploma.exception.EntityNotFoundException;
import gigachads.noenemies.diploma.exception.EntityNotUpdatedException;
import gigachads.noenemies.diploma.exception.StudentElectionsException;
import gigachads.noenemies.diploma.storage.jpa.entity.*;
import gigachads.noenemies.diploma.storage.jpa.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ElectionServiceImpl implements ElectionService {
    private final ElectionRepository electionRepository;
    private final CandidatureRepository candidatureRepository;
    private final CandidatureStageRepository candidatureStageRepository;
    private final CandidatureStageInfoRepository candidatureStageInfoRepository;
    private final StageRepository stageRepository;
    private final UserRepository userRepository;
    private final ElectionMapper electionMapper;
    private final CandidatureMapper candidatureMapper;

    @Override
    public Election createElection(ElectionCreate create) {
       return electionMapper.toDomain(electionRepository.save(electionMapper.toEntity(create)));
    }

    @Override
    public Election updateElection(ElectionId electionId, ElectionUpdate update) {
        var entity = findElectionEntityById(electionId).toBuilder();

        if (update.getDescription() != null){
            entity.description(update.getDescription());
        }

        if (update.getDeadline() != null){
            entity.deadline(update.getDeadline());
        }

        return electionMapper.toDomain(electionRepository.save(entity.build()));
    }

    @Override
    public List<Election> getElections(Integer limit) {
        return electionMapper.toDomain(electionRepository.findElectionsWithLimit(limit));
    }

    @Override
    public Election getElectionById(ElectionId id) {
        return electionMapper.toDomain(findElectionEntityById(id));
    }

    @Override
    public Election getCurrentElection() {
        return electionMapper.toDomain(findInProgressElection().orElseThrow(
                () -> new EntityNotFoundException("No election found with status" + ElectionStatus.IN_PROGRESS)
                )
        );
    }

    @Override
    public List<CandidatureStage> initiateElection(UserId officialId, ElectionId electionId) {
        ElectionEntity electionEntity = findElectionEntityById(electionId);
        if (findInProgressElection().isPresent()) {
            throw new StudentElectionsException("An election in progress already exists");
        }
        if (electionRepository.updateElectionStatus(electionId.getId(), ElectionStatus.IN_PROGRESS) == 0) {
            throw new EntityNotUpdatedException("Failed to initiate Election Entity: " + electionEntity);
        }
        List<CandidatureEntity> activeCandidatures = findAllActiveCandidatures();
        List<StageEntity> stages = findStagesByElectionId(electionId);
        if (stages.isEmpty()) {
            throw new StudentElectionsException("No stages created for election " + electionEntity);
        }
        if (activeCandidatures.isEmpty()) {
            throw new StudentElectionsException("No active candidates found");
        }
        List<CandidatureStageEntity> result = new ArrayList<>();
        for(StageEntity stageEntity : stages){
            for(CandidatureEntity candidatureEntity : activeCandidatures) {
                result.add(createCandidatureStage(stageEntity, candidatureEntity));
            }
        }

        log.info("Election {} was initiated by {}", electionEntity.getId(), officialId);
        return candidatureMapper.toCandidatureStageDomain(result);
    }

    @Override
    // TODO: create logic for winner
    public Election finishElectionById(ElectionId electionId) {
        var electionEntity = findElectionEntityById(electionId).toBuilder();

        return electionMapper.toDomain(electionRepository.save(electionEntity
                .status(ElectionStatus.COMPLETED)
                .build()));
    }

    @Override
    public void deleteElectionById(ElectionId electionId) {
        if (electionRepository.existsById(electionId.getAsUUID())){
            electionRepository.deleteById(electionId.getAsUUID());
        } else {
            throw new EntityNotUpdatedException("Election not found with id: " + electionId);
        }
    }

    private ElectionEntity findElectionEntityById(ElectionId id) {
        return electionRepository.findById(id.getId())
                .orElseThrow(() ->
                        new EntityNotUpdatedException("Election not found with id: " + id.getAsString()
                        )
                );
    }

    private CandidatureStageEntity createCandidatureStage(StageEntity stageEntity, CandidatureEntity candidatureEntity){
        CandidatureStageEntity candidatureStageEntity =  candidatureStageRepository.save(CandidatureStageEntity.builder()
                        .stage(stageEntity)
                        .candidature(candidatureEntity)
                        .stageInfo(candidatureStageInfoRepository.save(CandidatureStageInfoEntity.builder()
                                .link1("")
                                .link2("")
                                .description("")
                                .build()))
                .build());

        var result = findCandidatureStageById(CandidatureStageId.of(candidatureStageEntity.getId()));
        System.out.println(result);
        return result;
    }

    public Optional<ElectionEntity> findInProgressElection() {
        return electionRepository.findInProgressElection();
    }

    private CandidatureStageEntity findCandidatureStageById(CandidatureStageId candidatureStageId){
        return candidatureStageRepository.findById(candidatureStageId.getId()).orElseThrow(
                () -> new EntityNotFoundException("Candidature Stage not found with id " + candidatureStageId)
        );
    }

    private ElectionEntity findCurrentElectionEntity() {
        return electionRepository.findInProgressElection().orElseThrow(
                () -> new EntityNotFoundException("No election found with status " + ElectionStatus.IN_PROGRESS)
        );
    }

    private List<UserEntity> getAllActiveCandidates() {
        return (userRepository.findByRole(UserRole.ACTIVE_CANDIDATE));
    }

    private List<CandidatureEntity> findAllActiveCandidatures() {
        return candidatureRepository.findCandidaturesByUserRole(UserRole.ACTIVE_CANDIDATE);
    }

    private List<StageEntity> findStagesByElectionId(ElectionId electionId) {
        return stageRepository.findByElection_Id(electionId.getId());
    }
}

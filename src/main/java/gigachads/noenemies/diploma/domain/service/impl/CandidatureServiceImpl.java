package gigachads.noenemies.diploma.domain.service.impl;

import gigachads.noenemies.diploma.api.dto.CandidaturePlanUpdate;
import gigachads.noenemies.diploma.domain.mapper.CandidatureMapper;
import gigachads.noenemies.diploma.domain.model.*;
import gigachads.noenemies.diploma.domain.service.CandidatureService;
import gigachads.noenemies.diploma.exception.EntityNotFoundException;
import gigachads.noenemies.diploma.exception.InvalidRoleException;
import gigachads.noenemies.diploma.exception.StudentElectionsException;
import gigachads.noenemies.diploma.storage.jpa.entity.CandidatureEntity;
import gigachads.noenemies.diploma.storage.jpa.entity.CandidaturePlanEntity;
import gigachads.noenemies.diploma.storage.jpa.entity.ElectionEntity;
import gigachads.noenemies.diploma.storage.jpa.entity.UserEntity;
import gigachads.noenemies.diploma.storage.jpa.repository.CandidaturePlanRepository;
import gigachads.noenemies.diploma.storage.jpa.repository.CandidatureRepository;
import gigachads.noenemies.diploma.storage.jpa.repository.ElectionRepository;
import gigachads.noenemies.diploma.storage.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CandidatureServiceImpl implements CandidatureService {
    private final CandidatureRepository candidatureRepository;
    private final CandidaturePlanRepository candidaturePlanRepository;
    private final CandidatureMapper candidatureMapper;
    private final UserRepository userRepository;
    private final ElectionRepository electionRepository;

    @Override
    public List<Candidature> findAllActiveCandidatures() {
        return candidatureMapper.toDomain(candidatureRepository.findCandidaturesByUserRole(UserRole.ACTIVE_CANDIDATE));
    }

    @Override
    public void applyForCandidature(UserId userId) {
        UserEntity userEntity = findUserEntityById(userId);
        if (!userEntity.getRole().equals(UserRole.ACTIVE_STUDENT)){
            throw new InvalidRoleException(String.format("User must be %s to apply for candidature", UserRole.ACTIVE_STUDENT));
        }
        if(!electionRepository.existsCreatedElection()) {
            throw new StudentElectionsException(String.format("You cannot apply for candidature if there is no electio with status %s", ElectionStatus.CREATED));
        }
        if (userRepository.updateUserRoleById(userId.getId(), UserRole.APPLIED_FOR_CANDIDATURE) != 1){
            log.error("Something went wrong when updating user role");
        }
    }

    @Override
    public Candidature approveCandidature(UserId userId, UserId officialId) {
        CandidatureEntity candidatureEntity = candidatureRepository.save(CandidatureEntity.builder()
                .election(findCreatedElection())
                .user(findUserEntityById(userId))
                .approvedBy(findUserEntityById(officialId))
                .build());
        CandidaturePlanEntity planEntity = candidaturePlanRepository.save(CandidaturePlanEntity.builder()
                .description("")
                .slogan("")
                .instagramLink("")
                .telegramLink("")
                .candidature(candidatureEntity)
                .build());
        return candidatureMapper.toDomain(candidatureEntity.toBuilder()
                .plan(planEntity)
                .build());
    }

    @Override
    public CandidaturePlan updateCandidaturePlan(CandidaturePlanUpdate update, UserId userId) {
        if (findUserEntityById(userId).getRole() != UserRole.ACTIVE_CANDIDATE) {
            throw new InvalidRoleException(String.format("User must be %s to update his/her candidature plan", UserRole.ACTIVE_CANDIDATE));
        }

        var planEntityBuilder = findCandidaturePlanEntityByUserId(userId).toBuilder();

        if (update.getDescription() != null) {
            planEntityBuilder.description(update.getDescription());
        }
        if (update.getSlogan() != null) {
            planEntityBuilder.slogan(update.getSlogan());
        }
        if (update.getInstagramLink() != null) {
            planEntityBuilder.instagramLink(update.getInstagramLink().toString());
        }
        if (update.getTelegramLink() != null) {
            planEntityBuilder.telegramLink(update.getTelegramLink().toString());
        }

        return candidatureMapper.toCandidaturePlanDomain(candidaturePlanRepository.save(planEntityBuilder.build()));
    }


    private UserEntity findUserEntityById(UserId id) {
        return userRepository.findById(id.getId())
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with id: " + id.getAsString())
                );
    }

    private CandidatureEntity findCandidatureEntityById(CandidatureId candidatureId) {
        return candidatureRepository.findById(candidatureId.getId())
                .orElseThrow(() -> new EntityNotFoundException("Candidature not found with id " + candidatureId));
    }

    private CandidaturePlanEntity findCandidaturePlanEntityByUserId(UserId userId) {
        return candidaturePlanRepository.findByCandidature_User_Id(userId.getId())
                .orElseThrow(() -> new EntityNotFoundException("CandidaturePlan not found with for user id" + userId));
    }

    private CandidaturePlanEntity getCandidaturePlanEntityById(CandidaturePlanId id) {
        return candidaturePlanRepository.findById(id.getId())
                .orElseThrow(() -> new EntityNotFoundException("CandidaturePlan not found with id " + id));
    }

    private ElectionEntity findInProgressElection() {
        return electionRepository.findInProgressElection()
                .orElseThrow(() ->
                        new EntityNotFoundException("No Election In progress")
                );
    }

    private ElectionEntity findCreatedElection() {
        return electionRepository.findCreatedElection()
                .orElseThrow(() ->
                        new EntityNotFoundException("No Election with status created")
                );
    }

}

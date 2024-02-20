package gigachads.noenemies.diploma.domain.service.impl;

import gigachads.noenemies.diploma.domain.mapper.VoteMapper;
import gigachads.noenemies.diploma.domain.model.CandidatureStageId;
import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.model.Vote;
import gigachads.noenemies.diploma.domain.service.VoteService;
import gigachads.noenemies.diploma.exception.EntityNotFoundException;
import gigachads.noenemies.diploma.exception.StudentElectionsException;
import gigachads.noenemies.diploma.storage.jpa.entity.CandidatureStageEntity;
import gigachads.noenemies.diploma.storage.jpa.entity.UserEntity;
import gigachads.noenemies.diploma.storage.jpa.entity.VoteEntity;
import gigachads.noenemies.diploma.storage.jpa.repository.CandidatureStageRepository;
import gigachads.noenemies.diploma.storage.jpa.repository.UserRepository;
import gigachads.noenemies.diploma.storage.jpa.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class VoteServiceImpl implements VoteService {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final CandidatureStageRepository candidatureStageRepository;
    private final VoteMapper voteMapper;

    @Override
    public Vote voteForCandidatureStage(UserId electorId, CandidatureStageId candidatureStageId) {
        UserEntity elector = findUserEntityById(electorId);
        CandidatureStageEntity candidatureStage = findCandidatureStageEntityById(candidatureStageId);
        if(!candidatureStage.getStage().isVotable()){
            throw new StudentElectionsException("This stage is not open for voting");
        }

        if (hasUserVotedForStage(electorId, candidatureStageId)) {
            throw new IllegalStateException("User has already voted on the specified stage.");
        }

        return voteMapper.toDomain(voteRepository.save(VoteEntity.builder()
                        .elector(elector)
                        .candidatureStage(candidatureStage)
                .build()));
    }

    @Override
    public List<Vote> findUserVotesByUserId(UserId userId, int limit) {
        return voteMapper.toDomain(voteRepository.findAllByElectorIdOrderByCreatedAtDesc(userId.getId(), PageRequest.of(0, limit)));
    }


    private boolean hasUserVotedForStage(UserId electorId, CandidatureStageId candidatureStageId) {
        return voteRepository.existsByElectorIdAndCandidatureStage_Stage_Id(electorId.getId(), candidatureStageId.getId());
    }


    private UserEntity findUserEntityById(UserId id) {
        return userRepository.findById(id.getId())
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with id: " + id.getAsString())
                );
    }

    private CandidatureStageEntity findCandidatureStageEntityById(CandidatureStageId id){
        return candidatureStageRepository.findById(id.getId()).orElseThrow(
                () -> new EntityNotFoundException("CandidatureStage not found with id " + id)
        );
    }
}

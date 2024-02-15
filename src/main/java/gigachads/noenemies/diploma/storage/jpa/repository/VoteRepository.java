package gigachads.noenemies.diploma.storage.jpa.repository;

import gigachads.noenemies.diploma.storage.jpa.entity.CandidatureStageEntity;
import gigachads.noenemies.diploma.storage.jpa.entity.UserEntity;
import gigachads.noenemies.diploma.storage.jpa.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, UUID> {
    boolean existsByElectorAndCandidatureStage(UserEntity elector, CandidatureStageEntity candidatureStage);
}

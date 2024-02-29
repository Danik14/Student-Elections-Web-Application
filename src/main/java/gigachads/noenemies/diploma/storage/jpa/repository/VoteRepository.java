package gigachads.noenemies.diploma.storage.jpa.repository;

import gigachads.noenemies.diploma.storage.jpa.entity.VoteEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, UUID> {
    @Query("SELECT COUNT(v) > 0 FROM VoteEntity v WHERE v.elector.id = :userId AND v.candidatureStage.stage.id = :stageId")
    boolean hasUserVotedOnStage(@Param("userId") UUID userId, @Param("stageId") UUID stageId);

    List<VoteEntity> findAllByElectorIdOrderByCreatedAtDesc(UUID userId, Pageable pageable);
}

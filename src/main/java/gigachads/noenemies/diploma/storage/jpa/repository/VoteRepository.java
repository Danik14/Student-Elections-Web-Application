package gigachads.noenemies.diploma.storage.jpa.repository;

import gigachads.noenemies.diploma.storage.jpa.entity.VoteEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, UUID> {
    boolean existsByElectorIdAndCandidatureStage_Stage_Id(UUID userId, UUID stageId);

    List<VoteEntity> findAllByElectorIdOrderByCreatedAtDesc(UUID userId, Pageable pageable);
}

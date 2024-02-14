package gigachads.noenemies.diploma.storage.jpa.repository;

import gigachads.noenemies.diploma.domain.model.StageStatus;
import gigachads.noenemies.diploma.storage.jpa.entity.StageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StageRepository extends JpaRepository<StageEntity, UUID> {
    List<StageEntity> findByElection_Id(UUID electionId);

    Optional<StageEntity> findByElection_IdAndStatus(UUID electionId, StageStatus status);
}

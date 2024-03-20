package gigachads.noenemies.diploma.storage.jpa.repository;

import gigachads.noenemies.diploma.storage.jpa.entity.CandidatureStageInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidatureStageInfoRepository extends JpaRepository<CandidatureStageInfoEntity, UUID> {
    Optional<CandidatureStageInfoEntity> findByCandidatureStage_Id(UUID candidatureStageId);
}

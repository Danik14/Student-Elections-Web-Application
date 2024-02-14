package gigachads.noenemies.diploma.storage.jpa.repository;

import gigachads.noenemies.diploma.domain.model.StageStatus;
import gigachads.noenemies.diploma.storage.jpa.entity.CandidatureStageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CandidatureStageRepository extends JpaRepository<CandidatureStageEntity, UUID> {
    @Query("SELECT cs FROM CandidatureStageEntity cs " +
            "JOIN cs.stage s " +
            "WHERE s.election.id = :electionId")
    List<CandidatureStageEntity> findByElectionId(@Param("electionId") UUID electionId);

    @Query("SELECT cs FROM CandidatureStageEntity cs " +
            "JOIN cs.stage s " +
            "WHERE s.election.id = :electionId AND s.status = :status")
    List<CandidatureStageEntity> findByElectionIdAndStatus(
            @Param("electionId") UUID electionId,
            @Param("status") StageStatus status
    );
}

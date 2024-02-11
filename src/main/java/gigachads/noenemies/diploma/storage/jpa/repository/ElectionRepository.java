package gigachads.noenemies.diploma.storage.jpa.repository;

import gigachads.noenemies.diploma.domain.model.ElectionStatus;
import gigachads.noenemies.diploma.storage.jpa.entity.ElectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ElectionRepository extends JpaRepository<ElectionEntity, UUID> {
    @Query("SELECT e FROM ElectionEntity e WHERE e.status = 'IN_PROGRESS'")
    Optional<ElectionEntity> findInProgressElection();

    @Modifying
    @Query("UPDATE ElectionEntity e SET e.status = :newStatus WHERE e.id = :electionId")
    int updateElectionStatus(@Param("electionId") UUID electionId, @Param("newStatus") ElectionStatus newStatus);

    @Query("SELECT e FROM ElectionEntity e ORDER BY e.createdAt DESC LIMIT :limit")
    List<ElectionEntity> findElectionsWithLimit(int limit);
}

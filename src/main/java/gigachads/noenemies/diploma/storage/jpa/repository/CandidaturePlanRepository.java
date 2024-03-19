package gigachads.noenemies.diploma.storage.jpa.repository;

import gigachads.noenemies.diploma.storage.jpa.entity.CandidaturePlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CandidaturePlanRepository extends JpaRepository<CandidaturePlanEntity, UUID> {
    // @Query("SELECT cp FROM CandidaturePlanEntity cp WHERE cp.candidature.user.id = :userId")
    Optional<CandidaturePlanEntity> findByCandidature_User_Id(UUID userId);

    Optional<CandidaturePlanEntity> findByCandidature_Id(UUID userId);
}

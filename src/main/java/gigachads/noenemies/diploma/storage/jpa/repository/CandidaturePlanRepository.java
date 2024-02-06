package gigachads.noenemies.diploma.storage.jpa.repository;

import gigachads.noenemies.diploma.storage.jpa.entity.CandidaturePlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CandidaturePlanRepository extends JpaRepository<CandidaturePlanEntity, UUID> {
}

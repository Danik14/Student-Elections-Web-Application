package gigachads.noenemies.diploma.storage.jpa.repository;

import gigachads.noenemies.diploma.storage.jpa.entity.CandidatureStagePlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CandidatureStagePlanRepository extends JpaRepository<CandidatureStagePlanEntity, UUID> {
}

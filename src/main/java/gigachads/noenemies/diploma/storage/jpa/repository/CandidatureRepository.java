package gigachads.noenemies.diploma.storage.jpa.repository;

import gigachads.noenemies.diploma.domain.model.UserRole;
import gigachads.noenemies.diploma.storage.jpa.entity.CandidatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CandidatureRepository extends JpaRepository<CandidatureEntity, UUID> {
    @Query("SELECT c FROM CandidatureEntity c INNER JOIN c.user u WHERE u.role = :role")
    List<CandidatureEntity> findCandidaturesByUserRole(@Param("role") UserRole role);

}

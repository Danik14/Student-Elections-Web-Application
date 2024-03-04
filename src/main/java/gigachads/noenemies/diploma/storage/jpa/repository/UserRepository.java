package gigachads.noenemies.diploma.storage.jpa.repository;

import gigachads.noenemies.diploma.domain.model.UserRole;
import gigachads.noenemies.diploma.storage.jpa.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findUserByBarcode(String barcode);

    List<UserEntity> findByRole(UserRole role);

    Page<UserEntity> findByRole(UserRole userRole, Pageable pageable);

    @Modifying
    @Query("UPDATE UserEntity u SET u.role = :newRole WHERE u.id = :id")
    int updateUserRoleById(@Param("id") UUID id, @Param("newRole") UserRole newRole);
}

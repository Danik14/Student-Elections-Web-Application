package gigachads.noenemies.diploma.storage.jpa.repository;

import gigachads.noenemies.diploma.domain.model.UserRole;
import gigachads.noenemies.diploma.storage.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findUserByBarcode(String barcode);

    List<UserEntity> findByRole(UserRole role);
}

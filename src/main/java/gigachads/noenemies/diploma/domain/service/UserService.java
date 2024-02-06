package gigachads.noenemies.diploma.domain.service;

import gigachads.noenemies.diploma.domain.model.User;
import gigachads.noenemies.diploma.domain.model.UserCreate;
import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.model.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    User saveUser(UserCreate create);

    Page<User> findAllByPage(Pageable pageable);

    User getUserByBarcode(String barcode);

    User getUserById(UserId id);

    List<User> getAllCandidates();

    List<User> getAllActiveCandidates();

    User updateUserRole(UserId id, UserRole role);
}

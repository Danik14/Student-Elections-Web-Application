package gigachads.noenemies.diploma.domain.service;

import gigachads.noenemies.diploma.domain.model.User;
import gigachads.noenemies.diploma.domain.model.UserCreate;
import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.model.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    User saveUser(UserCreate create);

    Page<User> findAllByPage(Pageable pageable);

    Page<User> findAllByRoleAndPage(Pageable pageable, UserRole userRole);

    User getUserByBarcode(String barcode);

    User findUserById(UserId id);

    List<User> getAllCandidates();

    List<User> getAllActiveCandidates();

    User saveProfilePhoto(UserId userId, MultipartFile imageFile);

    byte[] getImage(String imageName) throws IOException;

    String deleteImage(String imageName) throws IOException;

    User updateUserRole(UserId id, UserRole role);
}

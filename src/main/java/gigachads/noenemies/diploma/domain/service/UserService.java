package gigachads.noenemies.diploma.domain.service;

import gigachads.noenemies.diploma.domain.model.User;
import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.model.UserRole;

import java.util.Collection;
import java.util.List;

public interface UserService {

    User getUserByBarcode(String barcode);

    User getUserById(UserId id);

    List<User> getAllCandidates();

    List<User> getAllActiveCandidates();

    User updateUserRole(UserId id, UserRole role);
}

package gigachads.noenemies.diploma.domain.service;

import gigachads.noenemies.diploma.domain.model.User;
import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.model.UserRole;

import java.util.List;

public interface UserService {
    List<User> getUsers(Integer limit);

    User getUserByBarcode(String barcode);

    User getUserById(UserId id);

    User updateUserRole(UserId id, UserRole role);
}

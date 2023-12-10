package gigachads.noenemies.diploma.domain.service.impl;

import gigachads.noenemies.diploma.domain.model.user.User;
import gigachads.noenemies.diploma.domain.model.user.UserId;
import gigachads.noenemies.diploma.domain.model.user.UserRole;
import gigachads.noenemies.diploma.domain.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<User> getUsers(Integer limit) {
        return null;
    }

    @Override
    public User getUserByBarcode(String barcode) {
        return null;
    }

    @Override
    public User getUserById(UserId id) {
        return null;
    }

    @Override
    public User updateUserRole(UserId id, UserRole role) {
        return null;
    }
}

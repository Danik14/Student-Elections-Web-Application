package gigachads.noenemies.diploma.domain.service.impl;

import gigachads.noenemies.diploma.domain.mapper.UserMapper;
import gigachads.noenemies.diploma.domain.model.User;
import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.model.UserCreate;
import gigachads.noenemies.diploma.domain.model.UserRole;
import gigachads.noenemies.diploma.domain.service.UserService;
import gigachads.noenemies.diploma.exception.EntityNotFoundException;
import gigachads.noenemies.diploma.storage.jpa.entity.UserEntity;
import gigachads.noenemies.diploma.storage.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User saveUser(UserCreate create) {
        return userMapper.toDomain(userRepository.save(userMapper.toEntity(create)));
    }

    @Override
    public Page<User> findAllByPage(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toDomain);
    }

    @Override
    public User getUserByBarcode(String barcode) {
        return userMapper.toDomain(userRepository.findUserByBarcode(barcode)
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with barcode: " + barcode)
                ));
    }

    @Override
    public User getUserById(UserId id) {
        return userMapper.toDomain(getUserEntityById(id));
    }

    @Override
    public List<User> getAllCandidates() {
        return userMapper.toDomain(userRepository.findByRole(UserRole.ACTIVE_CANDIDATE));
    }

    @Override
    public List<User> getAllActiveCandidates() {
        return userMapper.toDomain(userRepository.findByRole(UserRole.ACTIVE_CANDIDATE));
    }

    @Override
    public User updateUserRole(UserId id, UserRole role) {
        return null;
    }

    private UserEntity getUserEntityById(UserId id) {
        return userRepository.findById(id.getId())
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with id: " + id.getAsString())
                );
    }
}

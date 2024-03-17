package gigachads.noenemies.diploma.domain.service.impl;

import gigachads.noenemies.diploma.domain.mapper.UserMapper;
import gigachads.noenemies.diploma.domain.model.User;
import gigachads.noenemies.diploma.domain.model.UserCreate;
import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.model.UserRole;
import gigachads.noenemies.diploma.domain.service.UserService;
import gigachads.noenemies.diploma.exception.EntityNotFoundException;
import gigachads.noenemies.diploma.storage.jpa.entity.UserEntity;
import gigachads.noenemies.diploma.storage.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final FileImageService imageService;
    private final String photoDirectory;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           FileImageService imageService,
                           @Value("${users.profile.photo-image-directory}") String photoDirectory
                           ){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.imageService = imageService;
        this.photoDirectory = photoDirectory;
    }

    @Override
    public User saveUser(UserCreate create) {
        if (!userRepository.existsById(UUID.fromString(create.getId()))){
            return userMapper.toDomain(userRepository.save(userMapper.toEntity(create)));
        }
        return userMapper.toDomain(getUserEntityById(UserId.of(create.getId())));
    }

    @Override
    public Page<User> findAllByPage(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toDomain);
    }

    @Override
    public Page<User> findAllByRoleAndPage(Pageable pageable, UserRole userRole) {
        return userRepository.findByRole(userRole, pageable).map(userMapper::toDomain);
    }

    @Override
    public User getUserByBarcode(String barcode) {
        return userMapper.toDomain(userRepository.findUserByBarcode(barcode)
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with barcode: " + barcode)
                ));
    }

    @Override
    public User findUserById(UserId id) {
        System.out.println(id);
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
    public User saveProfilePhoto(UserId userId, MultipartFile photo) {
        UserEntity entity = getUserEntityById(userId);
        imageService.deleteImage(photoDirectory, entity.getFilePhotoName());
        return userMapper.toDomain(userRepository.save(entity.toBuilder()
                .filePhotoName(imageService.saveImageToStorage(photoDirectory, photo))
                .build()));
    }

    @Override
    public byte[] getImage(String imageName) throws IOException {
        Path imagePath = Path.of(photoDirectory, imageName);

        if (Files.exists(imagePath)) {
            return Files.readAllBytes(imagePath);
        } else {
            return null;
        }
    }

    @Override
    public String deleteImage(String imageName) throws IOException {
        Path imagePath = Path.of(photoDirectory, imageName);

        if (Files.exists(imagePath)) {
            Files.delete(imagePath);
            return "Success";
        } else {
            return "Failed";
        }
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

    private boolean exists(UserId id) {
        return userRepository.existsById(id.getId());
    }
}

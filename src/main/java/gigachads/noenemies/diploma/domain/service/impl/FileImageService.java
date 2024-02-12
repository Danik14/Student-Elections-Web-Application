package gigachads.noenemies.diploma.domain.service.impl;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@NoArgsConstructor
@Slf4j
public class FileImageService {

    @SneakyThrows
    public String saveImageToStorage(String uploadDirectory, MultipartFile imageFile) {
        String uniqueFileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();

        Path uploadPath = Path.of("src/main/" + uploadDirectory);
        Path filePath = uploadPath.resolve(uniqueFileName);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return uniqueFileName;
    }

    public byte[] getImage(String imageDirectory, String imageName) {
        Resource resource = new ClassPathResource(imageDirectory + imageName);

        if (resource.exists()) {
            try {
                return resource.getContentAsByteArray();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            log.error("Image does not exist: {}", resource.getFilename());
            return new byte[] {};
        }
    }

    public String deleteImage(String imageDirectory, String imageName) {
        Path imagePath = Path.of(imageDirectory, imageName);

        if (Files.exists(imagePath)) {
            try {
                Files.delete(imagePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return "Success";
        } else {
            return "Failed";
        }
    }
}

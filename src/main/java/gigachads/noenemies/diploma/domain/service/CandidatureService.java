package gigachads.noenemies.diploma.domain.service;

import gigachads.noenemies.diploma.api.dto.CandidaturePlanUpdate;
import gigachads.noenemies.diploma.domain.model.Candidature;
import gigachads.noenemies.diploma.domain.model.CandidaturePlan;
import gigachads.noenemies.diploma.domain.model.CandidatureStageId;
import gigachads.noenemies.diploma.domain.model.UserId;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CandidatureService {
    Candidature getCandidatureByUserId(UserId id);

    List<Candidature> findAllActiveCandidatures();

    void applyForCandidature(UserId studentId);

    void approveCandidature(UserId studentId, UserId officialId);

    String saveImageToStorage(String uploadDirectory, MultipartFile imageFile) throws IOException;

    byte[] getImage(String imageDirectory, String imageName) throws IOException;

    String deleteImage(String imageDirectory, String imageName) throws IOException;

    CandidaturePlan updateCandidaturePlan(CandidaturePlanUpdate update, UserId studentId);

    void voteForCandidate(UserId electorId, CandidatureStageId candidatureStageId);
}

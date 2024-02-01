package gigachads.noenemies.diploma.domain.service;

import gigachads.noenemies.diploma.api.dto.CandidaturePlanUpdate;
import gigachads.noenemies.diploma.domain.model.Candidature;
import gigachads.noenemies.diploma.domain.model.CandidaturePlan;
import gigachads.noenemies.diploma.domain.model.CandidatureStageId;
import gigachads.noenemies.diploma.domain.model.UserId;
import org.springframework.web.multipart.MultipartFile;

public interface CandidatureService {
    Candidature getCandidatureByUserId(UserId id);

    void applyForCandidature(UserId studentId);

    void approveCandidature(UserId studentId, UserId officialId);

    String saveImageToStorage(String uploadDirectory, MultipartFile imageFile);

    byte[] getImage(String imageDirectory, String imageName);

    String deleteImage(String imageDirectory, String imageName);

    CandidaturePlan updateCandidaturePlan(CandidaturePlanUpdate update, UserId studentId);

    void voteForCandidate(UserId electorId, CandidatureStageId candidatureStageId);
}

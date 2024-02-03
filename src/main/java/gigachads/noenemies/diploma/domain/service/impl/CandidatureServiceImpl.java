package gigachads.noenemies.diploma.domain.service.impl;

import gigachads.noenemies.diploma.api.dto.CandidaturePlanUpdate;
import gigachads.noenemies.diploma.domain.mapper.CandidatureMapper;
import gigachads.noenemies.diploma.domain.model.*;
import gigachads.noenemies.diploma.domain.service.CandidatureService;
import gigachads.noenemies.diploma.storage.jpa.repository.CandidatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CandidatureServiceImpl implements CandidatureService {
    private final CandidatureRepository candidatureRepository;
    private final CandidatureMapper candidatureMapper;

    @Override
    public Candidature getCandidatureByUserId(UserId id) {
        return null;
    }

    @Override
    public List<Candidature> findAllActiveCandidatures() {
        return candidatureMapper.toDomain(candidatureRepository.findCandidaturesByUserRole(UserRole.ACTIVE_CANDIDATE));
    }

    @Override
    public void applyForCandidature(UserId studentId) {

    }

    @Override
    public void approveCandidature(UserId studentId, UserId officialId) {

    }

    @Override
    public String saveImageToStorage(String uploadDirectory, MultipartFile imageFile) {
        return null;
    }

    @Override
    public byte[] getImage(String imageDirectory, String imageName) {
        return new byte[0];
    }

    @Override
    public String deleteImage(String imageDirectory, String imageName) {
        return null;
    }

    @Override
    public CandidaturePlan updateCandidaturePlan(CandidaturePlanUpdate update, UserId studentId) {
        return null;
    }

    @Override
    public void voteForCandidate(UserId electorId, CandidatureStageId candidatureStageId) {

    }
}

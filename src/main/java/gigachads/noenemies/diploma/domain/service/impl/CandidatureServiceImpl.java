package gigachads.noenemies.diploma.domain.service.impl;

import gigachads.noenemies.diploma.api.dto.CandidaturePlanUpdate;
import gigachads.noenemies.diploma.domain.model.Candidature;
import gigachads.noenemies.diploma.domain.model.CandidaturePlan;
import gigachads.noenemies.diploma.domain.model.CandidatureStageId;
import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.service.CandidatureService;
import org.springframework.stereotype.Service;

@Service
public class CandidatureServiceImpl implements CandidatureService {
    @Override
    public Candidature getCandidatureByUserId(UserId id) {
        return null;
    }

    @Override
    public void applyForCandidature(UserId studentId) {

    }

    @Override
    public void approveCandidature(UserId studentId, UserId officialId) {

    }

    @Override
    public CandidaturePlan updateCandidaturePlan(CandidaturePlanUpdate update, UserId studentId) {
        return null;
    }

    @Override
    public void voteForCandidate(UserId electorId, CandidatureStageId candidatureStageId) {

    }
}

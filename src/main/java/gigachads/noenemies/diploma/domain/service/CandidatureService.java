package gigachads.noenemies.diploma.domain.service;

import gigachads.noenemies.diploma.api.dto.CandidaturePlanUpdate;
import gigachads.noenemies.diploma.domain.model.Candidature;
import gigachads.noenemies.diploma.domain.model.CandidaturePlan;
import gigachads.noenemies.diploma.domain.model.CandidatureStageId;
import gigachads.noenemies.diploma.domain.model.UserId;

public interface CandidatureService {
    Candidature getCandidatureByUserId(UserId id);

    void applyForCandidature(UserId studentId);

    void approveCandidature(UserId studentId, UserId officialId);

    CandidaturePlan updateCandidaturePlan(CandidaturePlanUpdate update, UserId studentId);

    void voteForCandidate(UserId electorId, CandidatureStageId candidatureStageId);
}

package gigachads.noenemies.diploma.domain.service;

import gigachads.noenemies.diploma.domain.model.Candidature;
import gigachads.noenemies.diploma.domain.model.CandidatureStageId;
import gigachads.noenemies.diploma.domain.model.UserId;

public interface CandidatureService {
    Candidature getCandidatureByUserId(UserId id);

    void applyForCandidature(UserId id);

    void approveCandidature(UserId userId, UserId officialId);

    void voteForCandidate(UserId electorId, CandidatureStageId candidatureStageId);
}

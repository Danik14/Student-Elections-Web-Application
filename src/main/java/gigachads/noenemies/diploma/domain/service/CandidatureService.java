package gigachads.noenemies.diploma.domain.service;

import gigachads.noenemies.diploma.domain.model.candidature.Candidature;
import gigachads.noenemies.diploma.domain.model.candidatureStage.CandidatureStageId;
import gigachads.noenemies.diploma.domain.model.user.UserId;

public interface CandidatureService {
    Candidature getCandidatureByUserId(UserId id);

    void voteForCandidate(UserId electorId, CandidatureStageId candidatureStageId);
}

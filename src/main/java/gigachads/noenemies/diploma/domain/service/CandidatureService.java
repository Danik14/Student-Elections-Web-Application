package gigachads.noenemies.diploma.domain.service;

import gigachads.noenemies.diploma.api.dto.CandidaturePlanUpdate;
import gigachads.noenemies.diploma.domain.model.Candidature;
import gigachads.noenemies.diploma.domain.model.CandidatureId;
import gigachads.noenemies.diploma.domain.model.CandidaturePlan;
import gigachads.noenemies.diploma.domain.model.UserId;

import java.util.List;

public interface CandidatureService {
    Candidature findCandidatureById(CandidatureId candidatureId);

    List<Candidature> findAllActiveCandidatures();

    void applyForCandidature(UserId userId);

    Candidature approveCandidature(UserId userId, UserId officialId);

    CandidaturePlan findCandidaturePlanByCandidatureId(CandidatureId candidatureId);

    CandidaturePlan updateCandidaturePlan(CandidaturePlanUpdate update, UserId studentId);

    Candidature deactivateCandidature(CandidatureId candidatureId);
}

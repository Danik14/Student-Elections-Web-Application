package gigachads.noenemies.diploma.domain.service;

import gigachads.noenemies.diploma.api.dto.CandidaturePlanUpdate;
import gigachads.noenemies.diploma.domain.model.Candidature;
import gigachads.noenemies.diploma.domain.model.CandidaturePlan;
import gigachads.noenemies.diploma.domain.model.UserId;

import java.util.List;

public interface CandidatureService {
    List<Candidature> findAllActiveCandidatures();

    void applyForCandidature(UserId userId);

    Candidature approveCandidature(UserId userId, UserId officialId);

    CandidaturePlan updateCandidaturePlan(CandidaturePlanUpdate update, UserId studentId);
}

package gigachads.noenemies.diploma.domain.service;

import gigachads.noenemies.diploma.api.dto.CandidaturePlanUpdate;
import gigachads.noenemies.diploma.domain.model.*;

import java.util.List;

public interface CandidatureService {
    Candidature findCandidatureById(CandidatureId candidatureId);

    Candidature findCandidatureByUserId(UserId userId);

    List<Candidature> findAllActiveCandidatures();

    List<Candidature> findCandidaturesByElectionId(ElectionId electionId);

    void applyForCandidature(UserId userId);

    Candidature approveCandidature(UserId userId, UserId officialId, ElectionId electionId);

    CandidaturePlan findCandidaturePlanByCandidatureId(CandidatureId candidatureId);

    CandidaturePlan updateCandidaturePlan(CandidaturePlanUpdate update, UserId studentId);

    CandidaturePlan updateCandidaturePlan(CandidaturePlanUpdate update, CandidatureId candidatureId);

    Candidature deactivateCandidature(CandidatureId candidatureId);
}

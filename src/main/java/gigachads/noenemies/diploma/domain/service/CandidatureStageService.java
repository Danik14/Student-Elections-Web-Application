package gigachads.noenemies.diploma.domain.service;

import gigachads.noenemies.diploma.domain.model.CandidatureStage;
import gigachads.noenemies.diploma.domain.model.ElectionId;
import gigachads.noenemies.diploma.domain.model.StageStatus;

import java.util.List;

public interface CandidatureStageService {
    List<CandidatureStage> findCandidatureStagesByElectionId(ElectionId electionId);

    List<CandidatureStage> findCandidatureStagesByElectionIdAndStatus(ElectionId electionId, StageStatus stageStatus);

    List<CandidatureStage> findCurrentElectionCandidatureStagesByStatus(StageStatus stageStatus);
}

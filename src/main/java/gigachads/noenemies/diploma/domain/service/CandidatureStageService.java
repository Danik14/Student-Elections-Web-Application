package gigachads.noenemies.diploma.domain.service;

import gigachads.noenemies.diploma.api.dto.CandidatureStageInfoUpdate;
import gigachads.noenemies.diploma.domain.model.*;

import java.util.List;

public interface CandidatureStageService {
    List<CandidatureStage> findCandidatureStagesByElectionId(ElectionId electionId);

    List<CandidatureStage> findCandidatureStagesByElectionIdAndStatus(ElectionId electionId, StageStatus stageStatus);

    List<CandidatureStage> findCurrentElectionCandidatureStagesByStatus(StageStatus stageStatus);

    CandidatureStage findCandidatureStageById(CandidatureStageId candidatureStageId);

    CandidatureStageInfo findCandidatureStageInfoById(CandidatureStageInfoId id);

    CandidatureStageInfo findInfoByCandidatureStageId(CandidatureStageId candidatureStageId);

    CandidatureStageInfo updateCandidatureStageInfo(CandidatureStageInfoId id, CandidatureStageInfoUpdate update);
}

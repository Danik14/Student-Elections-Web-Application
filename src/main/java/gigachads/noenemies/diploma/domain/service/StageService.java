package gigachads.noenemies.diploma.domain.service;

import gigachads.noenemies.diploma.api.dto.StageCreate;
import gigachads.noenemies.diploma.domain.model.ElectionId;
import gigachads.noenemies.diploma.domain.model.Stage;
import gigachads.noenemies.diploma.domain.model.StageId;

import java.util.List;

public interface StageService {
    List<Stage> findStagesByElectionId(ElectionId electionId);

    Stage createElectionStage(ElectionId electionId, StageCreate create);

    Stage findStageById(StageId stageId);

    Stage findCurrentStageByElectionId(ElectionId electionId);

    Stage findCurrentElectionCurrentStage();
}

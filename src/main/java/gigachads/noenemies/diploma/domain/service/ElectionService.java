package gigachads.noenemies.diploma.domain.service;

import gigachads.noenemies.diploma.api.dto.ElectionCreate;
import gigachads.noenemies.diploma.domain.model.*;

import java.util.List;

public interface ElectionService {
        Election createElection(ElectionCreate create);

        List<Election> getElections(Integer limit);

        Election getElectionById(ElectionId id);

        Election getCurrentElection();

        List<CandidatureStage> findCandidatureStagesByElectionId(ElectionId electionId);

        List<CandidatureStage> findCandidatureStagesByElectionIdAndStatus(ElectionId electionId, StageStatus stageStatus);

        List<CandidatureStage> initiateElection(UserId officialId, ElectionId electionId);
}

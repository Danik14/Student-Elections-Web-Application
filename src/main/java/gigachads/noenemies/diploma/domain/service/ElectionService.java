package gigachads.noenemies.diploma.domain.service;

import gigachads.noenemies.diploma.api.dto.ElectionCreate;
import gigachads.noenemies.diploma.domain.model.Election;
import gigachads.noenemies.diploma.domain.model.ElectionId;
import gigachads.noenemies.diploma.domain.model.UserId;

import java.util.List;

public interface ElectionService {
    // 1 - goToStageNumber(Integer stageNumber) - for ex. go to stage 2.

        Election createElection(ElectionCreate create);

        List<Election> getElections(Integer limit);

        Election getElectionById(ElectionId id);

        void initiateElection(UserId officialId, ElectionId electionId);
}

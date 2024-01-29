package gigachads.noenemies.diploma.domain.service;

import gigachads.noenemies.diploma.domain.model.Election;
import gigachads.noenemies.diploma.domain.model.ElectionId;
import gigachads.noenemies.diploma.domain.model.UserId;

public interface ElectionService {
    // 1 - goToStageNumber(Integer stageNumber) - for ex. go to stage 2.

        Election createElection(Election election);

        Election getElectionById(ElectionId electionId);

        void initiateElection(UserId officialId, ElectionId electionId);


}

package gigachads.noenemies.diploma.domain.service;

import gigachads.noenemies.diploma.domain.model.election.Election;
import gigachads.noenemies.diploma.domain.model.election.ElectionId;
import gigachads.noenemies.diploma.domain.model.user.UserId;

public interface ElectionService {
    // 1 - goToStageNumber(Integer stageNumber) - for ex. go to stage 2.

        Election createElection(Election election);

        Election getElectionById(ElectionId electionId);

        void initiateElection(UserId officialId, ElectionId electionId);


}

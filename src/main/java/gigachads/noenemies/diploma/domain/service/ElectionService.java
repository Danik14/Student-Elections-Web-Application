package gigachads.noenemies.diploma.domain.service;

import gigachads.noenemies.diploma.api.dto.ElectionCreate;
import gigachads.noenemies.diploma.domain.model.CandidatureStage;
import gigachads.noenemies.diploma.domain.model.Election;
import gigachads.noenemies.diploma.domain.model.ElectionId;
import gigachads.noenemies.diploma.domain.model.UserId;

import java.util.List;

public interface ElectionService {
        Election createElection(ElectionCreate create);

        List<Election> getElections(Integer limit);

        Election getElectionById(ElectionId id);

        List<CandidatureStage> initiateElection(UserId officialId, ElectionId electionId);
}

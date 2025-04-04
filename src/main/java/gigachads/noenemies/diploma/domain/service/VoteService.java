package gigachads.noenemies.diploma.domain.service;

import gigachads.noenemies.diploma.domain.model.CandidatureStageId;
import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.model.Vote;

import java.util.List;

public interface VoteService {
   Vote voteForCandidatureStage(UserId electorId, CandidatureStageId candidatureStageId);

   List<Vote> findUserVotesByUserId(UserId userId, int limit);
}

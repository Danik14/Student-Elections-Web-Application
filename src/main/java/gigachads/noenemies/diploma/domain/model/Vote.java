package gigachads.noenemies.diploma.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class Vote {
    @NonNull
    private VoteId id;

    @NonNull
    private User elector;
}

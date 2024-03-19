package gigachads.noenemies.diploma.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder(toBuilder = true)
public class CandidatureStageInfo {
    @NonNull
    private String description;
    @NonNull
    private String link1;
    @NonNull
    private String link2;
}

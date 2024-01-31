package gigachads.noenemies.diploma.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@AllArgsConstructor
@Data
public class CandidatureStageId {
    @NonNull
    private final UUID id;

    public static CandidatureStageId of(UUID id) {
        return new CandidatureStageId(id);
    }

    public static CandidatureStageId of(String id) {
        return of(UUID.fromString(id));
    }

    public String getAsString() {
        return id.toString();
    }
}

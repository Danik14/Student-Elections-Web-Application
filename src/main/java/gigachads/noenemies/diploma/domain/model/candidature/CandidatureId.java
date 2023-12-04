package gigachads.noenemies.diploma.domain.model.candidature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@AllArgsConstructor
@Data
public class CandidatureId {
    @NonNull
    private final UUID id;

    public static CandidatureId of(UUID id) {
        return new CandidatureId(id);
    }

    public static CandidatureId of(String id) {
        return of(UUID.fromString(id));
    }

    public String getAsString() {
        return id.toString();
    }
}

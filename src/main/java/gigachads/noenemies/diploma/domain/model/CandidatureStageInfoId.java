package gigachads.noenemies.diploma.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@AllArgsConstructor
@Data
public class CandidatureStageInfoId {
    @NonNull
    private final UUID id;

    @JsonCreator
    public static CandidatureStageInfoId of(UUID id) {
        return new CandidatureStageInfoId(id);
    }

    public static CandidatureStageInfoId of(String id) {
        return of(UUID.fromString(id));
    }

    @JsonValue
    public UUID getAsUUID() {
        return getId();
    }

    public String getAsString() {
        return id.toString();
    }
}

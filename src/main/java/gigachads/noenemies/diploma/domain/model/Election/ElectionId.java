package gigachads.noenemies.diploma.domain.model.Election;

import gigachads.noenemies.diploma.domain.model.stage.StageId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@AllArgsConstructor
@Data
public class ElectionId {
    @NonNull
    private final UUID id;

    public static StageId of(UUID id) {
        return new StageId(id);
    }

    public static StageId of(String id) {
        return of(UUID.fromString(id));
    }

    public String getAsString() {
        return id.toString();
    }
}

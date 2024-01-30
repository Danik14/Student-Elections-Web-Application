package gigachads.noenemies.diploma.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@AllArgsConstructor
@Data
public class ElectionId {
    @NonNull
    private final UUID id;

    public static ElectionId of(UUID id) {
        return new ElectionId(id);
    }

    public static ElectionId of(String id) {
        return of(UUID.fromString(id));
    }

    public String getAsString() {
        return id.toString();
    }
}

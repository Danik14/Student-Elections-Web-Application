package gigachads.noenemies.diploma.domain.model.user;

import lombok.Builder;
import lombok.NonNull;

import java.util.UUID;

@Builder(toBuilder = true)
public class UserId {
    @NonNull
    private final UUID id;

    public static UserId of(UUID id) {
        return new UserId(id);
    }

    public static UserId of(String id) {
        return of(UUID.fromString(id));
    }

    public String getAsString() {
        return id.toString();
    }
}

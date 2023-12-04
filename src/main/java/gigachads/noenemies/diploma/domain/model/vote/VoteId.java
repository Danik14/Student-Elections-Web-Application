package gigachads.noenemies.diploma.domain.model.vote;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@AllArgsConstructor
@Data
public class VoteId {
    @NonNull
    private final UUID id;

    public static VoteId of(UUID id) {
        return new VoteId(id);
    }

    public static VoteId of(String id) {
        return of(UUID.fromString(id));
    }

    public String getAsString() {
        return id.toString();
    }
}

package gigachads.noenemies.diploma.domain.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class UserCreate {
    @NotNull
    private final String id;
    @NotNull
    private final String firstName;
    @NotNull
    private final String lastName;
    @NotNull
    private final String email;
}

package gigachads.noenemies.diploma.api.dto;

import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.model.UserRole;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder(toBuilder = true)
public class UserResponse {
    @NonNull
    private final UserId id;
    @NonNull
    private final String barcode;
    @NonNull
    private final String email;
    @NonNull
    private final String firstName;
    @NonNull
    private final String lastName;
    private final byte[] photo;
    @NonNull
    private final UserRole role;
}

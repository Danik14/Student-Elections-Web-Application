package gigachads.noenemies.diploma.api.dto;

import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.model.UserRole;
import lombok.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    @NonNull
    private UserId id;
    @NonNull
    private String barcode;
    @NonNull
    private String email;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private byte[] photo;
    @NonNull
    private UserRole role;
}

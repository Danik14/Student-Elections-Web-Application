package gigachads.noenemies.diploma.domain.model.user;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Builder
public class User {
    @NonNull
    private final UserId id;
    @NonNull
    private final String barcode;
    @NonNull
    private final String firstName;
    @NonNull
    private final String lastName;
    @NonNull
    private final String middleName = "";
    @NonNull
    private final UserRole role;

    public static class UserBuilder{
        public UserId id(UserId id) {
            return id;
        }

        public UserId id(String id) {
            return UserId.of(id);
        }
    }
}

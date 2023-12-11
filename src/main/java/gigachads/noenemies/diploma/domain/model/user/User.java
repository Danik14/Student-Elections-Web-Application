package gigachads.noenemies.diploma.domain.model.user;

import lombok.*;

@EqualsAndHashCode
@ToString
@Builder
@Getter
public class User {
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
    @NonNull
    private final UserRole role;

    public static class UserBuilder{
        public UserBuilder id(UserId id) {
            this.id = id;
            return this;
        }

        public UserBuilder id(String id) {
            return id(UserId.of(id));
        }
    }
}

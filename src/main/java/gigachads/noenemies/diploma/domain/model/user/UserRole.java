package gigachads.noenemies.diploma.domain.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    USER(Collections.emptySet()),
    STUDENT(mergePermissions(
            USER,
            UserPermission.STUDENT_READ,
            UserPermission.STUDENT_UPDATE,
            UserPermission.STUDENT_DELETE,
            UserPermission.STUDENT_CREATE
    )
    ),
    CANDIDATE(mergePermissions(STUDENT,
            UserPermission.CANDIDATE_READ,
            UserPermission.CANDIDATE_UPDATE,
            UserPermission.CANDIDATE_DELETE,
            UserPermission.CANDIDATE_CREATE
    )
    ),
    ELECTION_OFFICIAL(mergePermissions(CANDIDATE,
            UserPermission.ELECTION_OFFICIAL_READ,
            UserPermission.ELECTION_OFFICIAL_UPDATE,
            UserPermission.ELECTION_OFFICIAL_DELETE,
            UserPermission.ELECTION_OFFICIAL_CREATE
    )
    ),
    SUPER_ADMIN(mergePermissions(ELECTION_OFFICIAL,
            UserPermission.SUPER_ADMIN_READ,
            UserPermission.SUPER_ADMIN_UPDATE,
            UserPermission.SUPER_ADMIN_DELETE,
            UserPermission.SUPER_ADMIN_CREATE
    )
    );

    private final Set<UserPermission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

    private static Set<UserPermission> mergePermissions(UserRole parentRole, UserPermission... additionalPermissions) {
        Set<UserPermission> allPermissions = EnumSet.copyOf(parentRole.getPermissions());
        allPermissions.addAll(Set.of(additionalPermissions));
        return Collections.unmodifiableSet(allPermissions);
    }
}

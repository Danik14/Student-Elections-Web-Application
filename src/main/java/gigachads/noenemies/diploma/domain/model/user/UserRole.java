package gigachads.noenemies.diploma.domain.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    USER(Collections.emptySet()),
    VERIFIED_USER(
            Set.of(
                    UserPermission.VERIFIED_READ,
                    UserPermission.VERIFIED_UPDATE,
                    UserPermission.VERIFIED_DELETE,
                    UserPermission.VERIFIED_CREATE)),
    ADMIN(
            Set.of(
                    UserPermission.ADMIN_READ,
                    UserPermission.ADMIN_UPDATE,
                    UserPermission.ADMIN_DELETE,
                    UserPermission.ADMIN_CREATE));

    private final Set<UserPermission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
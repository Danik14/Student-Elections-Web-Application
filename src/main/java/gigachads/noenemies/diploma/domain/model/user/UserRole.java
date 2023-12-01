package gigachads.noenemies.diploma.domain.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static gigachads.noenemies.diploma.domain.model.user.UserPermission.*;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    USER(Collections.emptySet()),
    VERIFIED_USER(
            Set.of(
                    VERIFIED_READ,
                    VERIFIED_UPDATE,
                    VERIFIED_DELETE,
                    VERIFIED_CREATE)),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE));

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
package gigachads.noenemies.diploma.domain.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserPermission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),

    VERIFIED_READ("verified:read"),
    VERIFIED_UPDATE("verified:update"),
    VERIFIED_CREATE("verified:create"),
    VERIFIED_DELETE("verified:delete");

    @Getter
    private final String permission;
}
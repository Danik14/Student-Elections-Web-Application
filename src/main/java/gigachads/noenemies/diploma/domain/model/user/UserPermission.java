package gigachads.noenemies.diploma.domain.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserPermission {

    STUDENT_READ("student:read"),
    STUDENT_UPDATE("student:update"),
    STUDENT_CREATE("student:create"),
    STUDENT_DELETE("student:delete"),

    CANDIDATE_READ("candidate:read"),
    CANDIDATE_UPDATE("candidate:update"),
    CANDIDATE_CREATE("candidate:create"),
    CANDIDATE_DELETE("candidate:delete"),

    ELECTION_OFFICIAL_READ("election_official:read"),
    ELECTION_OFFICIAL_UPDATE("election_official:update"),
    ELECTION_OFFICIAL_CREATE("election_official:create"),
    ELECTION_OFFICIAL_DELETE("election_official:delete"),

    SUPER_ADMIN_READ("super_admin:read"),
    SUPER_ADMIN_UPDATE("super_admin:update"),
    SUPER_ADMIN_CREATE("super_admin:create"),
    SUPER_ADMIN_DELETE("super_admin:delete");

    @Getter
    private final String permission;
}
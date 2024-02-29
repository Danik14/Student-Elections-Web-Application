package gigachads.noenemies.diploma.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserSortField {
    ID("id"),
    FIRSTNAME("firstName"),
    LASTNAME("lastName");

    private final String databaseFieldName;
}
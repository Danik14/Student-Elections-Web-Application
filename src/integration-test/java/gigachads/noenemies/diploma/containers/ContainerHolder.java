package gigachads.noenemies.diploma.containers;

import org.junit.jupiter.api.extension.Extension;
import org.testcontainers.containers.PostgreSQLContainer;

public class ContainerHolder implements Extension {
    private static final PostgreSQLContainer<?> postgreSql = PostgreSQLContainerBuilder.getInstance()
            .startCommonContainer();

    public static final PostgreSQLContainer<?> getPostgreSQL() {
        return postgreSql;
    }
}


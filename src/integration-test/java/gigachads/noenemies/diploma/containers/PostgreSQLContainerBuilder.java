package gigachads.noenemies.diploma.containers;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgreSQLContainerBuilder {
    private static final String IMAGE_VERSION = "postgres:12";

    public static PostgreSQLContainerBuilder getInstance() {
        return new PostgreSQLContainerBuilder();
    }

    public PostgreSQLContainer<?> startCommonContainer() {
        var container = new PostgreSQLContainer<>(IMAGE_VERSION)
                .withCreateContainerCmdModifier(cmd -> cmd.withName("student-elections-IT-database"))
                .withDatabaseName("student_elections_IT")
                .withUsername("testcontainers")
                .withPassword("testcontainers")
                .withExposedPorts(5432)
                .withReuse(true);
        container.start();
        System.setProperty("spring.datasource.url", container.getJdbcUrl());
        System.setProperty("spring.datasource.username", container.getUsername());
        System.setProperty("spring.datasource.password", container.getPassword());
        System.setProperty("spring.datasource.driver-class-name", container.getDriverClassName());
        return container;
    }
}

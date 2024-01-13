package gigachads.noenemies.diploma.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ConfigurationBeans {
//    @Bean // Remove in production
//    public FlywayMigrationStrategy flywayCleanMigrationStrategy() {
//        return flyway -> {
//            flyway.clean();
//            flyway.migrate();
//        };
//    }
}

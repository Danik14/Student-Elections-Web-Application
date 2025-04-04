package gigachads.noenemies.diploma.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

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

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter
                = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(100000);
        filter.setIncludeHeaders(false);
        filter.setAfterMessagePrefix("REQUEST DATA: ");
        return filter;
    }
}

package gigachads.noenemies.diploma.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Profile("test")
@Configuration
public class TestConfiguration {

//    @EnableWebSecurity
//    public static class DisableSecurityConfig  {
//        @Bean
//        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//            http.authorizeHttpRequests(authorize -> authorize
//                    .anyRequest().permitAll()
//            ).oauth2Login(AbstractHttpConfigurer::disable);
//            return http.build();
//        }
//    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/**");
    }
}

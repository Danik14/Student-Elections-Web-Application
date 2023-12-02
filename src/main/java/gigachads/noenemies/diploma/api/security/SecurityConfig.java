package gigachads.noenemies.diploma.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
//        return http.authorizeHttpRequests(r -> r
//                        .requestMatchers("/api/v1/admin/**").hasRole(ADMIN.name())
//                        .requestMatchers("/api/v1/auth/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .oauth2Login(oauth2 -> oauth2
//                        .loginPage("/api/v1/auth/login")
//                        .defaultSuccessUrl("/api/v1/loginsuccess")
//                ).logout(l -> l
//                        .logoutSuccessUrl("/api/v1/logoutsucess")
//                        .permitAll()
//                )
//
//                .build();

        return http.authorizeHttpRequests(r -> r
                                        .anyRequest().authenticated()
                )
                .build();

    }
}
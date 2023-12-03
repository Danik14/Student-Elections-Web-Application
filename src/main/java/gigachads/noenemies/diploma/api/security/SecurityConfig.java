package gigachads.noenemies.diploma.api.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        return http.authorizeHttpRequests(r -> r
                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/v1/auth/**").permitAll()
                .anyRequest().authenticated())
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/1"))
                .logout(l -> l
                        .logoutSuccessUrl("/api/v1/logoutsucess")
                        .permitAll())

                .build();

    }
    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // return http.build();
    // }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new SimpleUrlAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                    Authentication authentication) throws IOException, ServletException {
                System.out.println("********");
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                System.out.println("User authenticated: " + userDetails.getUsername());

                super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }
}
// http://localhost:8080/api/v1/login/oauth2/code/?
// code=0.ATwA8xWPFeCDBkmCTGm9xQ2dYRH0bvZYG6VKmxFe68nk1xU8AAA.AgABAAIAAAAmoFfGtYxvRrNriQdPKIZ-AgDs_wUA9P-nGdpqq7d9WByEj0AfxdL09Rbud2sRvZNgYqbkpnLcDCYTaR6lfWR5lLa9TFPVgxkQYphdrgFl8y0LVjEqclkJDBkJSRyyjWTaXIAKCsPRPpDjobF9Ph5SzLokZLtbXbINrklJPc-G4iltOSh617vZBNz-FVdvqVZ7KTAOcyzDiJ051MGNyWilTpPIXJ4OOMJQWLxY-xZLcVPyvPc_bXntVTq5nk2UCnAARfbSkl9kE7CVtroG_Tbemz1afsO_-r-5HLIInX7Qupv3Mc6lDQ5S5CeSaI6_SkZmBsY-DZ4UKPBsvDZ8FaVZEeOcRhqqWzy1rVg7qjnu-aymucH-MPuU2YnmtUKrJtq_wr40LmV_npsFWn_oCIp5FRtJdtPeM6aoq-_hgAbFm_6nsxNsxgHQ1qRd_cMeC51_PVNoRPcMzm9OP8rZRmicgJLs_3SQid8peYfvGJNNi0YCwUnt5l9Gm6Yj5nBH0iNWojKPYtD-aHa40lPqSl1jaicbh9dOwJggOdeVW0VUw32FP99hxQ20istqYA-iYyAxIREmlM677kMm0ojQeaFYA3OD1C2_lDbrTbQfAmJ9sHDXwV650EtK-2E4tWjs4H9yz2K0V-RupehA_UAeTqonX3SJKRoFHLssDZudxN7EaIkN-PbfAyiyB1dPQEokTBWGBvYzAJp-La4WAus2BlEBUSaHhrLYY5hRsWKeA8HC9GIdKjehvyZTy4Qu8DiBjjcfxVETijqWQWZrkLAiSfb7Z7eh9rahuPfNiwy1
// &state=taenqcPHHjwZw_BexQV-aRlkKWZNfe2E6WutetDa3mE%3d
// &session_state=954c4563-d54d-46d5-8b9a-fd277cdc6f8f#

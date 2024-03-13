package gigachads.noenemies.diploma;

import gigachads.noenemies.diploma.api.dto.ElectionCreate;
import gigachads.noenemies.diploma.domain.model.Election;
import gigachads.noenemies.diploma.domain.service.ElectionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class TestHelper {
    private final ElectionService electionService;

    public LocalDateTime toDefaultTime(String localDateTime){
        return LocalDateTime.parse(localDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }

    @Getter
    private final Principal testSuperAdminOauth2TokenPrincipal = new OAuth2AuthenticationToken(
            new DefaultOAuth2User(
                    Stream.of("OIDC_USER), SCOPE_email, SCOPE_openid, SCOPE_profile").map(SimpleGrantedAuthority::new).collect(Collectors.toList()),
                    Map.of("sub", "gZJnr1mzmbe98A6nn1NCwrvy4UCnbrRnIyYZYc4xIyo",
                            "preferred_username", "211360@astanait.edu.kz",
                            "given_name", "Daniyar",
                            "family_name", "Chapagan"),
                    "sub"
            ),
            Stream.of("OIDC_USER), SCOPE_email, SCOPE_openid, SCOPE_profile").map(SimpleGrantedAuthority::new).collect(Collectors.toList()),
            "stub"
    );

    @Getter
    private final Principal testActiveStudentOauth2TokenPrincipal = new OAuth2AuthenticationToken(
            new DefaultOAuth2User(
                    Stream.of("OIDC_USER), SCOPE_email, SCOPE_openid, SCOPE_profile").map(SimpleGrantedAuthority::new).collect(Collectors.toList()),
                    Map.of("sub", "rvy4UCnbrRnIyYZYc4xIyogZJnr1mzmbe98A6nn1NCw",
                            "preferred_username", "211362@astanait.edu.kz",
                            "given_name", "Daniyar2",
                            "family_name", "Chapagan2"),
                    "sub"
            ),
            Stream.of("OIDC_USER), SCOPE_email, SCOPE_openid, SCOPE_profile").map(SimpleGrantedAuthority::new).collect(Collectors.toList()),
            "stub"
    );

    public Election createElection(ElectionCreate electionCreate) {
        return electionService.createElection(electionCreate);
    }

    public Election createDefaultElection(){
        return createElection(
                ElectionCreate.builder()
                .description("Default Description")
                .deadline(toDefaultTime("2025-03-31T23:59:59"))
                .build()
        );
    }
}

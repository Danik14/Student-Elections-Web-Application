package gigachads.noenemies.diploma;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TestHelper {
    private final Principal testOauth2TokenPrincipal = new OAuth2AuthenticationToken(
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

    public Principal getTestOauth2TokenPrincipal(){
        return testOauth2TokenPrincipal;
    }
}

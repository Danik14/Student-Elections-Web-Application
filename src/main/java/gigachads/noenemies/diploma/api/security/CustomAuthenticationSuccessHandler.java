package gigachads.noenemies.diploma.api.security;

import gigachads.noenemies.diploma.domain.model.User;
import gigachads.noenemies.diploma.domain.model.UserCreate;
import gigachads.noenemies.diploma.domain.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
        log.info("User authenticated: {}", oidcUser.getUserInfo().getClaims());
        userService.saveUser(parseUserInfo(oidcUser));
        super.onAuthenticationSuccess(request, response, authentication);
    }

    private UserCreate parseUserInfo(DefaultOidcUser user) {
        return UserCreate.builder()
                .firstName(user.getGivenName())
                .lastName(user.getFamilyName())
                .email(user.getEmail())
                .build();
    }

}

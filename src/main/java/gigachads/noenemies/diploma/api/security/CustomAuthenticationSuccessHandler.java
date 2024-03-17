package gigachads.noenemies.diploma.api.security;

import gigachads.noenemies.diploma.domain.model.UserCreate;
import gigachads.noenemies.diploma.domain.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Objects;

import static gigachads.noenemies.diploma.HelperClass.convertToUUIDFromString;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @NonNull
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticatedPrincipal oidcUser = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        System.out.println(convertToUUIDFromString(oidcUser.getName()));
        log.info("User authenticated: {}", oidcUser);
        userService.saveUser(parseUserInfo(oidcUser));

        getRedirectStrategy().sendRedirect(request, response, "http://localhost:3000/");
    }

    private UserCreate parseUserInfo(OAuth2AuthenticatedPrincipal oidcUser) {
        return UserCreate.builder()
                .id(Objects.requireNonNull(convertToUUIDFromString(oidcUser.getName())).toString())
                .firstName(oidcUser.getAttribute("given_name"))
                .lastName(oidcUser.getAttribute("family_name"))
                .email(oidcUser.getAttribute("email"))
                .build();
    }
}

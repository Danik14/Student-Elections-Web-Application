package gigachads.noenemies.diploma.api.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        System.out.println("********");
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
        System.out.println("User authenticated: " + userDetails.getUserInfo().getClaims());
        System.out.println("User authenticated: " + userDetails.getUserInfo().getGivenName() + userDetails.getUserInfo().getFamilyName());

        // Redirect to the originally requested URL after successful authentication
        super.onAuthenticationSuccess(request, response, authentication);
    }
}

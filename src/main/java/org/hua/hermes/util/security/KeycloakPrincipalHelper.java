package org.hua.hermes.util.security;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Helper class that gives instant access to attributes of Keycloak's principal.
 * This class must not be used in requests where a user can be an anonymous user.
 * @author <a href="mailto:nikosdelta@protonmail.com">Nick Dimitrakopoulos</a>
 * @since 15-12-2020
 */
@SuppressWarnings("unchecked")
public class KeycloakPrincipalHelper
{
    public static KeycloakPrincipal<KeycloakSecurityContext> getKeycloakPrincipal()
    {
        return (KeycloakPrincipal<KeycloakSecurityContext>) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    public static AccessToken getToken()
    {
        return getKeycloakPrincipal()
                .getKeycloakSecurityContext()
                .getToken();
    }

}

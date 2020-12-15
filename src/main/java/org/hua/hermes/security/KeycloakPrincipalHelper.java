package org.hua.hermes.security;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

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

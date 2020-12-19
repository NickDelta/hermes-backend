package org.hua.hermes.util.persistence;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author <a href="mailto:nikosdelta@protonmail.com">Nick Dimitrakopoulos</a>
 */
public class AuditorAwareImpl implements AuditorAware<String>
{
    //This implementation solves a possible conflict that exists due to Keycloak's custom Authentication object type.
    @Override
    public Optional<String> getCurrentAuditor()
    {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}

package org.hua.hermes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class PersistenceConfig
{
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

    class AuditorAwareImpl implements AuditorAware<String>
    {
        @Override
        public Optional<String> getCurrentAuditor()
        {
            return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
        }
    }
}

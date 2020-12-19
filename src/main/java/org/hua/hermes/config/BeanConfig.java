package org.hua.hermes.config;

import org.hua.hermes.exception.ResourceNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
public class BeanConfig
{
    /* Hack: SpEL doesn't support lambda syntax.
     * Throwing ResourceNotFoundException through Optional in SpEL can be achieved by injecting a supplier bean.
     */
    @Bean
    public Supplier<ResourceNotFoundException> resourceNotFoundExceptionSupplier()
    {
        return () -> new ResourceNotFoundException();
    }
}

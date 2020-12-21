package org.hua.hermes.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hua.hermes.exception.ResourceNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
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

    @Bean
    public ObjectMapper jacksonObjectMapper(){
        return new ObjectMapper().setDateFormat(new SimpleDateFormat("dd-MM-yyyy'Τ'HH:mm:ss"));
    }
}

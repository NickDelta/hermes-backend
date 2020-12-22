package org.hua.hermes.config;

import org.hua.hermes.exception.ResourceNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

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

    //Support validation messages in property files
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}

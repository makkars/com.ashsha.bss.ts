package com.ashsha.bss.ts.configuration;

import com.ashsha.bss.ts.business.handler.impl.common.InstituteOperation;
import com.ashsha.bss.ts.business.handler.impl.common.TransactionalWrapperOperation;
import com.ashsha.bss.ts.entity.converters.EntityConverter;
import com.ashsha.bss.ts.entity.converters.IEntityConverter;
import com.ashsha.bss.ts.rest.service.validators.InstituteValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan (basePackages = { "com.ashsha.bss.ts" })
public class ApplicationConfiguration
{
    @Bean (name = "entityConverter")
    public IEntityConverter getEntityConverter()
    {
        return new EntityConverter();
    }

    @Bean (name = "instituteOperation")
    public InstituteOperation getInstituteOperation()
    {
        return new InstituteOperation();
    }

    @Bean (name = "instituteValidator")
    public InstituteValidator getInstituteValidator()
    {
        return new InstituteValidator();
    }

    @Bean (name = "transactionalWrapperOperation")
    public TransactionalWrapperOperation getTransactionalWrapperOperation()
    {
        return new TransactionalWrapperOperation();
    }

    @Bean (name = "messageSource")
    public ReloadableResourceBundleMessageSource messageSource()
    {
        ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
        messageBundle.setBasename("classpath:messages/messages");
        messageBundle.setDefaultEncoding("UTF-8");
        return messageBundle;
    }

}

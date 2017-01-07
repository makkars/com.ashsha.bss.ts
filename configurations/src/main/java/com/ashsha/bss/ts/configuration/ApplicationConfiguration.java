package com.ashsha.bss.ts.configuration;

import com.ashsha.bss.ts.business.handler.impl.common.InstituteOperation;
import com.ashsha.bss.ts.business.handler.impl.common.TransactionalWrapperOperation;
import com.ashsha.bss.ts.entity.converters.EntityConverter;
import com.ashsha.bss.ts.entity.converters.IEntityConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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

    @Bean (name = "transactionalWrapperOperation")
    public TransactionalWrapperOperation getTransactionalWrapperOperation()
    {
        return new TransactionalWrapperOperation();
    }
}

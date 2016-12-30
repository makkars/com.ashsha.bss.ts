package com.ashsha.bss.ts.configuration;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceArrayPropertyEditor;

@Configuration
public class MappingConfiguration
{
    @Bean (name = "beanMapper")
    public DozerBeanMapper dozerBeanMapperBean(@Value ("classpath*:mappings/*mappings.xml") Resource[] resources) throws Exception
    {
        final DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean = new DozerBeanMapperFactoryBean();

        // Other configurations
        dozerBeanMapperFactoryBean.setMappingFiles(resources);
        dozerBeanMapperFactoryBean.afterPropertiesSet();
        return (DozerBeanMapper) dozerBeanMapperFactoryBean.getObject();

        // Another way to set resource property
        //ResourceArrayPropertyEditor editor = new ResourceArrayPropertyEditor();
        //editor.setAsText("classpath*:/dozer/**/*.xml");
        //dozerBeanMapperFactoryBean.setMappingFiles((Resource[]) editor.getValue());
    }
}

package com.ashsha.bss.ts.tuitionServer.main;

import com.ashsha.bss.ts.configuration.ApplicationConfiguration;
import com.ashsha.bss.ts.models.Institute;
import com.ashsha.bss.ts.persistence.service.InstituteService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class HibernateMain
{
    public static void main(String[] args)
    {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        InstituteService instituteService = (InstituteService) context.getBean("instituteService");

        System.out.print("Got instituteService Object: "+instituteService);
        instituteService.getAllInstitutes();
    }
}

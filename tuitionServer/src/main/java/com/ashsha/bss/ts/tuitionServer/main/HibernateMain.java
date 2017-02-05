package com.ashsha.bss.ts.tuitionServer.main;

import com.ashsha.bss.ts.business.handler.impl.common.TransactionalWrapperOperation;
import com.ashsha.bss.ts.configuration.ApplicationConfiguration;
import com.ashsha.bss.ts.entity.db.common.Institute;
import com.ashsha.bss.ts.entity.dto.IInstituteDTO;
import com.ashsha.bss.ts.entity.dto.common.InstituteDTO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class HibernateMain
{
    public static void main(String[] args)
    {
//        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
//
//        String[] beans = context.getBeanDefinitionNames();
//
//                for (String bean : beans)
//                {
//                    System.out.println("Bean name   is: " + bean + "\nBean object is: " + context.getBean(bean));
//                }

//        TransactionalWrapperOperation transactionalWrapperOperation = (TransactionalWrapperOperation) context.getBean("transactionalWrapperOperation");
        //        transactionalWrapperOperation.registerInstitute(getInstitute());
//        transactionalWrapperOperation.deleteById(Institute.class, 22L);
    }

    public static IInstituteDTO getInstitute()
    {
        IInstituteDTO i = new InstituteDTO();
        //        i.setId(4L);
        i.setName("MineInstitutes");
        i.setAdminName("Myselfs");
        i.setAdminContactPrimary(129191211245L);
        i.setAdminContactSecondary(123132343323L);
        i.setAppName("MI");
        i.setBranches("UttamNagar");
        i.setImagesLocationOnServer("server");
        return i;
    }
}

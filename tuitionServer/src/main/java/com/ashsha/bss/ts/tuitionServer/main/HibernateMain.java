package com.ashsha.bss.ts.tuitionServer.main;

import com.ashsha.bss.ts.configuration.ApplicationConfiguration;
import com.ashsha.bss.ts.entity.db.common.Institute;
import com.ashsha.bss.ts.entity.db.service.impl.JpaDao;
import com.ashsha.bss.ts.persistence.service.InstituteService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class HibernateMain
{
    public static void main(String[] args)
    {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        JpaDao jpaDao = (JpaDao) context.getBean("genericDao");
        jpaDao.persist(getInstitute());

        Long count = jpaDao.count(Institute.class);

        System.out.print(count + "------------------------------" + "\n" + jpaDao.getAll(Institute.class));


        //        InstituteService instituteService = (InstituteService) context.getBean("instituteService");
        //
        //        System.out.print(instituteService.getAllInstitutes());
        //
        //        System.out.print("------------------");
        //        System.out.print(instituteService.getById(2L));
    }

    public static Institute getInstitute()
    {
        Institute i = new Institute();
//        i.setId(4L);
        i.setName("MineInstitute");
        i.setAdminName("Myself");
        i.setAdminContactPrimary(9912112L);
        i.setAdminContactSecondary(33333323L);
        i.setAppName("MI");
        i.setBranches("UttamNagar");
        i.setImagesLocationOnServer("server");
        return i;
    }
}

package com.ashsha.bss.ts.business.handler.impl.common;

import com.ashsha.bss.ts.entity.db.core.Entity;
import com.ashsha.bss.ts.entity.dto.IInstituteDTO;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TransactionalWrapperOperation implements ApplicationContextAware
{
    private ApplicationContext applicationContext;

    @Autowired
    private InstituteOperation instituteOperation;

    /**
     * This method can be used from any OperationBase implementation
     * @param dbEntity : DB Entity Class for Persistence into Table
     * @param id : ID to delete
     */
    public void deleteById(Class<? extends Entity> dbEntity, Long id)
    {
        instituteOperation.delete(dbEntity, id);
    }

    public void registerInstitute(IInstituteDTO instituteDTO)
    {
        //        InstituteOperation instituteOperation = (InstituteOperation) applicationContext.getBean("instituteOperation");
        instituteOperation.registerInstitute(instituteDTO);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        this.applicationContext = applicationContext;
    }
}

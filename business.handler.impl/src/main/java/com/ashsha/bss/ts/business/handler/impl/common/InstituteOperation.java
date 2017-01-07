package com.ashsha.bss.ts.business.handler.impl.common;

import com.ashsha.bss.ts.business.handler.common.IInstituteOperation;
import com.ashsha.bss.ts.business.handler.impl.OperationBase;
import com.ashsha.bss.ts.entity.db.common.Institute;
import com.ashsha.bss.ts.entity.db.core.Entity;
import com.ashsha.bss.ts.entity.dto.IInstituteDTO;
import com.ashsha.bss.ts.entity.dto.common.InstituteDTO;

public class InstituteOperation extends OperationBase implements IInstituteOperation
{
    @Override
    public void registerInstitute(IInstituteDTO instituteDTO)
    {
        Institute institute = convert(Institute.class, instituteDTO);
        registerInstitute(institute);
    }

    @Override
    public Institute getInstituteEntity(Long id)
    {
        return getById(id);
    }

    @Override
    public InstituteDTO getInstituteById(Long id)
    {
        Institute institute = (Institute) getInstituteEntity(id);
        return convert(InstituteDTO.class, institute);
    }

    @Override
    protected Class<? extends Entity> getEntityType()
    {
        return Institute.class;
    }

    protected void preRegister(Institute institute)
    {
        // Intentionally Left empty. Yet to Decide steps for it...
    }

    protected void postRegister(Institute institute)
    {
        // Intentionally Left empty. Yet to Decide steps for it...
    }

    protected void registerInternal(Institute institute)
    {
        getDao().persist(institute);
    }

    private void registerInstitute(Institute institute)
    {
        preRegister(institute);
        registerInternal(institute);
        postRegister(institute);
    }
}

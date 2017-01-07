package com.ashsha.bss.ts.business.handler.common;

import com.ashsah.bss.ts.business.handler.IOperationBase;
import com.ashsha.bss.ts.entity.db.common.Institute;
import com.ashsha.bss.ts.entity.dto.IInstituteDTO;
import com.ashsha.bss.ts.entity.dto.common.InstituteDTO;

public interface IInstituteOperation extends IOperationBase
{
    /**
     *
     * @param instituteDTO : Persistence given institue object into database
     */

    // Todo : Need to decide what would be the return type, for now saving it with
    void registerInstitute(IInstituteDTO instituteDTO);

    /**
     *
     * @param id : Institute id
     * @return : Institute DB Entity for other entity dao class to access it with conversion.
     */
    Institute getInstituteEntity(Long id);

    /**
     *
     * @param id : Institute id
     * @return : Institute Business Object to be used in other operations.
     */
    InstituteDTO getInstituteById(Long id);
}

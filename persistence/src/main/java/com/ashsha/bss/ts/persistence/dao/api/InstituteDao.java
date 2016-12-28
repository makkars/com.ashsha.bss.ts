package com.ashsha.bss.ts.persistence.dao.api;

import com.ashsha.bss.ts.models.Institute;
import java.util.List;

public interface InstituteDao
{
    public void saveInstitute(Institute institute);

    public List<Institute> getAll();

    /**
     *
     * @param id : This id is public key of the Institute Table
     * @return : {@link com.ashsha.bss.ts.models.Institute}
     *            Specific Institute Object corresponding to input public key
     */
    public Institute getById(Long id);
}

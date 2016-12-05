package com.ashsha.bss.ts.persistence.dao.api;

import com.ashsha.bss.ts.models.Institute;
import java.util.List;

public interface InstituteDao
{
    public void saveInstitute(Institute institute);

    public List<Institute> getAll();
}

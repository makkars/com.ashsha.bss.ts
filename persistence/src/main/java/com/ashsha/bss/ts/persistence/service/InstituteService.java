package com.ashsha.bss.ts.persistence.service;

import com.ashsha.bss.ts.models.Institute;
import com.ashsha.bss.ts.persistence.dao.api.InstituteDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service ("instituteService")
@Transactional
public class InstituteService
{
    @Autowired
    private InstituteDao instituteDao;

    public List<Institute> getAllInstitutes()
    {
        return instituteDao.getAll();
    }

    public Institute getById(Long id)
    {
        return instituteDao.getById(id);
    }
}

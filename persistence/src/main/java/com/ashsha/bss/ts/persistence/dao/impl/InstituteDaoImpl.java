package com.ashsha.bss.ts.persistence.dao.impl;

import com.ashsha.bss.ts.models.Institute;
import com.ashsha.bss.ts.persistence.dao.api.AbstractDao;
import com.ashsha.bss.ts.persistence.dao.api.InstituteDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaQuery;


@Repository ("instituteDao")
public class InstituteDaoImpl extends AbstractDao implements InstituteDao
{
    public void saveInstitute(Institute institute)
    {
        persist(institute);
    }

    public List<Institute> getAll()
    {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<Institute>  instituteCriteriaQuery = criteriaBuilder.createQuery(Institute.class);
        Root<Institute> instituteRoot = instituteCriteriaQuery.from(Institute.class);
        instituteCriteriaQuery.select(instituteRoot);

        return null;
    }
}

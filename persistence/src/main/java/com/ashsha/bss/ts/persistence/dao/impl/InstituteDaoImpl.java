package com.ashsha.bss.ts.persistence.dao.impl;

import com.ashsha.bss.ts.models.Institute;
import com.ashsha.bss.ts.persistence.dao.api.AbstractDao;
import com.ashsha.bss.ts.persistence.dao.api.InstituteDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import org.springframework.stereotype.Repository;

@Repository ("instituteDao")
public class InstituteDaoImpl extends AbstractDao implements InstituteDao
{
    public void saveInstitute(Institute institute)
    {
        persist(institute);
    }

    public List<Institute> getAll()
    {
        EntityManager entitymanager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entitymanager.getCriteriaBuilder();
        CriteriaQuery<Institute> instituteCriteriaQuery = criteriaBuilder.createQuery(Institute.class);
        Root<Institute> instituteRoot = instituteCriteriaQuery.from(Institute.class);
        instituteCriteriaQuery.select(instituteRoot);
        TypedQuery<Institute> q = entitymanager.createQuery(instituteCriteriaQuery);

        List<Institute> allInstitute = q.getResultList();
        return allInstitute;
    }

    @Override
    public Institute getById(Long id)
    {
        EntityManager entitymanager = entityManagerFactory.createEntityManager();
        Institute allInstitute = entitymanager.find(Institute.class, id);
        //        Institute allInstitute = entitymanager.getReference(Institute.class, id);
        return allInstitute;
    }

    public void get()
    {
        EntityManager entitymanager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entitymanager.getCriteriaBuilder();

        CriteriaQuery<Institute> instituteCriteriaQuery = criteriaBuilder.createQuery(Institute.class);
        Root<Institute> instituteRoot = instituteCriteriaQuery.from(Institute.class);
        instituteCriteriaQuery.select(instituteRoot);

        Selection<String> instituteName = instituteRoot.get("name");
        instituteCriteriaQuery.select(criteriaBuilder.construct(Institute.class, instituteName));

        TypedQuery<Institute> q = entitymanager.createQuery(instituteCriteriaQuery);

        List<Institute> allInstitute = q.getResultList();
    }

    public List<Institute> getAllSimple()
    {
        EntityManager entitymanager = entityManagerFactory.createEntityManager();
        Query q = entitymanager.createQuery("SELECT id FROM institute");
        List<Institute> allInstitute = q.getResultList();
        return allInstitute;
    }
}

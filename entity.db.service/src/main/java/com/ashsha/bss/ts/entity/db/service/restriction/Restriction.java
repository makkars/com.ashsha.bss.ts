package com.ashsha.bss.ts.entity.db.service.restriction;

import java.util.Arrays;
import java.util.Iterator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

public abstract class Restriction implements IRestriction
{

    protected CriteriaBuilder criteriaBuilder;

    private String path;

    public Restriction(String path)
    {
        this.path = path;
    }

    public Restriction()
    {
    }

    public <T> Predicate apply(From<T, T> from)
    {
        Predicate predicate = null;

        String[] properties = path.split("\\.");

        Iterator<String> iterator = Arrays.asList(properties).iterator();

        while (iterator.hasNext())
        {
            String property = iterator.next();
            if (iterator.hasNext())
            {
                from = from.join(property);
            }
            else
            {
                predicate = createPredicate(from, property);
            }
        }

        return predicate;
    }

    protected <T> Predicate createPredicate(From<T, T> from, String property)
    {
        return null;
    }

    public CriteriaBuilder getCriteriaBuilder()
    {
        return criteriaBuilder;
    }

    public void setCriteriaBuilder(CriteriaBuilder criteriaBuilder)
    {
        this.criteriaBuilder = criteriaBuilder;
    }

}

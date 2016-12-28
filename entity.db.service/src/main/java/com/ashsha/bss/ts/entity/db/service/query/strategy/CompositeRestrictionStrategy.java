package com.ashsha.bss.ts.entity.db.service.query.strategy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

public abstract class CompositeRestrictionStrategy
{

    CriteriaBuilder criteriaBuilder;

    List<Predicate> predicates;

    public CompositeRestrictionStrategy()
    {
        predicates = new ArrayList<>();
    }

    public void add(Predicate predicate)
    {
        predicates.add(predicate);
    }

    public void setCriteriaBuilder(CriteriaBuilder criteriaBuilder)
    {
        this.criteriaBuilder = criteriaBuilder;
    }

    protected Predicate[] getPredicatesArray()
    {
        Predicate[] predicateArray = new Predicate[predicates.size()];
        for (int i = 0; i < predicates.size(); i++)
        {
            predicateArray[i] = predicates.get(i);
        }

        return predicateArray;
    }

    abstract public Predicate apply();

}

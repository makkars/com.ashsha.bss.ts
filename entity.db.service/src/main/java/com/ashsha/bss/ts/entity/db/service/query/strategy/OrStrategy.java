package com.ashsha.bss.ts.entity.db.service.query.strategy;

import javax.persistence.criteria.Predicate;

public class OrStrategy extends CompositeRestrictionStrategy
{

    @Override
    public Predicate apply()
    {
        return criteriaBuilder.or(getPredicatesArray());
    }
}

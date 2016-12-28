package com.ashsha.bss.ts.entity.db.service.query.strategy;

import javax.persistence.criteria.Predicate;

public class AndStrategy extends CompositeRestrictionStrategy
{

    @Override
    public Predicate apply()
    {
        return criteriaBuilder.and(getPredicatesArray());
    }

}

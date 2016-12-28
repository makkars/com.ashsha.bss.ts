package com.ashsha.bss.ts.entity.db.service.restriction;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

public class EqualRestriction extends Restriction
{
    private Object value;

    public EqualRestriction(String path, Object value)
    {
        super(path);
        this.value = value;
    }

    @Override
    protected <T> Predicate createPredicate(From<T, T> from, String property)
    {
        Expression<String> path = getStringExpression(from, property);
        return criteriaBuilder.equal(path, value);
    }

    <T> Expression<String> getStringExpression(From<T, T> from, String property)
    {
        return from.get(property);
    }

}

package com.ashsha.bss.ts.entity.db.service.restriction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

public interface IRestriction
{

    public <T> Predicate apply(From<T, T> from);

    public void setCriteriaBuilder(CriteriaBuilder criteriaBuilder);
}

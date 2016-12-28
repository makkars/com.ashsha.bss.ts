package com.ashsha.bss.ts.entity.db.service.restriction;

import com.ashsha.bss.ts.entity.db.service.query.strategy.CompositeRestrictionStrategy;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

public class CompositeRestriction extends Restriction
{

    private List<IRestriction> restrictions;
    private CompositeRestrictionStrategy strategy;

    public CompositeRestriction(CompositeRestrictionStrategy strategy, IRestriction... restrictions)
    {
        this.strategy = strategy;
        this.restrictions = new ArrayList<>();
        if (restrictions != null)
        {
            for (IRestriction restriction : restrictions)
            {
                this.restrictions.add(restriction);
            }
        }
    }

    public boolean hasRestrictions()
    {
        return !this.restrictions.isEmpty();
    }

    @Override
    public <T> Predicate apply(From<T, T> from)
    {

        for (IRestriction restriction : restrictions)
        {
            restriction.setCriteriaBuilder(criteriaBuilder);
            Predicate predicate = restriction.apply(from);
            strategy.add(predicate);
        }

        strategy.setCriteriaBuilder(criteriaBuilder);
        return strategy.apply();
    }

    public void addRestriction(IRestriction restriction)
    {
        restrictions.add(restriction);
    }

}

package com.ashsha.bss.ts.entity.db.service.impl;

import com.ashsha.bss.ts.entity.db.core.Entity;
import com.ashsha.bss.ts.entity.db.core.Enums;
import com.ashsha.bss.ts.entity.db.service.IDAO;
import com.ashsha.bss.ts.entity.db.service.query.OffsetLimit;
import com.ashsha.bss.ts.entity.db.service.query.strategy.AndStrategy;
import com.ashsha.bss.ts.entity.db.service.restriction.CompositeRestriction;
import com.ashsha.bss.ts.entity.db.service.restriction.EqualRestriction;
import com.ashsha.bss.ts.entity.db.service.restriction.IRestriction;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * The Class JpaDao.
 *
 *         JPA Implementation for the Data Access Object reference.
 */
@Repository ("genericDao")
public class JpaDao implements IDAO
{
    private static final String ID = "id";

    // Currently i am managing entityManager from EMF .
    // need to move to JTA . for which more concept brain storming is required.
    // have few linked saved . will check those.
    @Autowired
    //    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * Instantiates a new jpa dao.
     */
    public JpaDao()
    {
    }

    public EntityManager getEntityManager()
    {
        return entityManager;
    }

    @Override
    public boolean exists(Class<? extends Entity> entityType, Long id)
    {
        IRestriction restriction = new EqualRestriction(ID, id);
        return countByRestriction(entityType, restriction) > 0L;
    }

    @Override
    public boolean isActive(Class<? extends Entity> entityType, Long id)
    {
        IRestriction keyRestriction = new EqualRestriction(ID, id);
        IRestriction statusRestriction = new EqualRestriction("status", Enums.EntityStatus.Active);

        CompositeRestriction allRestrictions = new CompositeRestriction(new AndStrategy(), keyRestriction, statusRestriction);
        return countByRestriction(entityType, allRestrictions) > 0L;
    }

    // You need not to define begin and end transaction . it's defined at operation level on each method or class level
    @Override
    public void persist(Entity entity)
    {
        org.hibernate.Session session = (Session) getEntityManager().getDelegate();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.flush();
        session.getTransaction().commit();
    }

    @Override
    public void refresh(Entity entity)
    {
        getEntityManager().refresh(entity);
    }

    @Override
    public void remove(Entity entity)
    {
        Session session = getEntityManager().unwrap(Session.class);
        getEntityManager().remove(entity);
        session.flush();
    }

    @Override
    public <E extends Entity, K> E findById(Class<E> entityType, K id)
    {
        return (E) getEntityManager().find(entityType, id);
    }

    @Override
    public <E extends Entity> List<E> findByProperty(Class<E> entityType, String propertyName, Object value)
    {
        TypedQuery<E> typedQuery = createQueryByProperty(entityType, propertyName, value);
        return typedQuery.getResultList();
    }

    @Override
    public <E extends Entity> List<E> findByProperty(Class<E> entityType, String propertyName, Object value, OffsetLimit offsetLimit)
    {
        TypedQuery<E> typedQuery = createQueryByProperty(entityType, propertyName, value);
        typedQuery.setFirstResult(offsetLimit.getOffset());
        typedQuery.setMaxResults(offsetLimit.getLimit());
        return typedQuery.getResultList();
    }

    @Override
    public <E> E findSingleResultByProperty(Class<E> entityType, String propertyName, Object value)
    {
        TypedQuery<E> typedQuery = createQueryByProperty(entityType, propertyName, value);
        List<E> resultList = typedQuery.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public <E extends Entity, K> E getSingleResult(Class<E> entityType)
    {
        TypedQuery<E> typedQuery = createQuery(entityType);
        typedQuery.setFirstResult(0);
        typedQuery.setMaxResults(1);
        return typedQuery.getSingleResult();
    }

    @Override
    public <E extends Entity> List<E> getAll(Class<E> entityType)
    {
        TypedQuery<E> typedQuery = createQuery(entityType);
        return typedQuery.getResultList();
    }

    @Override
    public Entity merge(Entity entity)
    {
        return getEntityManager().merge(entity);
    }

    @Override
    public void flush()
    {
        getEntityManager().flush();
    }

    @Override
    public <T> TypedQuery<T> createTypedQuery(String sqlString, Class<T> resultClass)
    {
        return getEntityManager().createQuery(sqlString, resultClass);
    }

    @Override
    public Query createQuery(String sqlString)
    {
        return getEntityManager().createQuery(sqlString);
    }

    @Override
    public <T> Query createNativeQuery(String sqlString, Class<T> resultClass)
    {
        return getEntityManager().createNativeQuery(sqlString, resultClass);
    }

    @Override
    public <E extends Entity, K> E getReference(Class<E> entityType, K id)
    {
        return getEntityManager().getReference(entityType, id);
    }

    @Override
    public void detach(Entity entity)
    {
        getEntityManager().detach(entity);
    }

    @Override
    public <E extends Entity> List<E> getRange(Class<E> entityType, OffsetLimit offsetLimit, String sortFieldName, Enums.SortDirection direction)
    {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = builder.createQuery(entityType);
        Root<E> root = criteriaQuery.from(entityType);
        if (sortFieldName != null)
        {
            criteriaQuery.orderBy(direction == Enums.SortDirection.ASC ? builder.asc(root.get(sortFieldName))
                    : builder.desc(root.get(sortFieldName)));
        }
        criteriaQuery.select(root);

        TypedQuery<E> typedQuery = getEntityManager().createQuery(criteriaQuery);
        typedQuery.setFirstResult(offsetLimit.getOffset());
        typedQuery.setMaxResults(offsetLimit.getLimit());
        return typedQuery.getResultList();
    }

    @Override
    public <T extends Entity> TypedQuery<T> findByRestriction(Class<T> entityType, IRestriction restriction)
    {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityType);
        Root<T> root = criteriaQuery.from(entityType);

        restriction.setCriteriaBuilder(criteriaBuilder);
        Predicate predicate = restriction.apply(root);

        criteriaQuery.where(predicate);

        return getEntityManager().createQuery(criteriaQuery);
    }

    @Override
    public <T extends Entity> List<T> findByRestriction(Class<T> entityType, IRestriction restriction, OffsetLimit offsetLimit)
    {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityType);
        Root<T> root = criteriaQuery.from(entityType);

        restriction.setCriteriaBuilder(criteriaBuilder);
        Predicate predicate = restriction.apply(root);

        criteriaQuery.where(predicate);

        TypedQuery<T> typedQuery = getEntityManager().createQuery(criteriaQuery);
        typedQuery.setFirstResult(offsetLimit.getOffset());
        typedQuery.setMaxResults(offsetLimit.getLimit());
        return typedQuery.getResultList();
    }

    @Override
    public long count(Class<? extends Entity> entityType)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<? extends Entity> entityRoot = criteriaQuery.from(entityType);
        criteriaQuery.select(criteriaBuilder.count(entityRoot));
        return getEntityManager().createQuery(criteriaQuery).getSingleResult();
    }

    @Override public <E extends Entity> E findLast(Class<E> entityType)
    {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = builder.createQuery(entityType);
        Root<E> root = criteriaQuery.from(entityType);
        criteriaQuery.orderBy(builder.desc(root.get(ID)));
        criteriaQuery.select(root);

        TypedQuery<E> typedQuery = getEntityManager().createQuery(criteriaQuery);
        typedQuery.setMaxResults(1);
        E result = typedQuery.getSingleResult();
        return result;
    }

    /**
     * Count by restriction.
     *
     * @param entityType
     *            the entity type
     * @param restriction
     *            the restriction
     * @return the long
     */
    protected long countByRestriction(Class<? extends Entity> entityType, IRestriction restriction)
    {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<? extends Entity> entityRoot = criteriaQuery.from(entityType);

        restriction.setCriteriaBuilder(criteriaBuilder);
        Predicate predicate = restriction.apply(entityRoot);
        criteriaQuery.where(predicate);

        criteriaQuery.select(criteriaBuilder.count(entityRoot));

        return getEntityManager().createQuery(criteriaQuery).getSingleResult();
    }

    /**
     * Creates the query by property.
     *
     * @param <T>
     *            the generic type
     * @param entityType
     *            the entity type
     * @param propertyName
     *            the property name
     * @param value
     *            the value
     * @return the typed query
     */
    private <T> TypedQuery<T> createQueryByProperty(Class<T> entityType, String propertyName, Object value)
    {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityType);
        Root<T> root = criteriaQuery.from(entityType);

        EqualRestriction equalRestriction = new EqualRestriction(propertyName, value);
        equalRestriction.setCriteriaBuilder(criteriaBuilder);
        Predicate predicate = equalRestriction.apply(root);

        criteriaQuery.where(predicate);

        return getEntityManager().createQuery(criteriaQuery);
    }

    /**
     * Creates the query by property.
     *
     * @param <T>
     *            the generic type
     * @param entityType
     *            the entity type
     * @return the typed query
     */
    private <T> TypedQuery<T> createQuery(Class<T> entityType)
    {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityType);
        Root<T> root = criteriaQuery.from(entityType);
        criteriaQuery.select(root);

        return getEntityManager().createQuery(criteriaQuery);
    }
}

package com.ashsha.bss.ts.entity.db.service;

import com.ashsha.bss.ts.entity.db.core.Entity;
import com.ashsha.bss.ts.entity.db.core.Enums;
import com.ashsha.bss.ts.entity.db.service.query.OffsetLimit;
import com.ashsha.bss.ts.entity.db.service.restriction.IRestriction;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * The Interface IDAO.
 */
public interface IDAO
{

    /**
     * Exists.
     *
     * @param entityType
     *            the entity type
     * @param id
     *            the id
     * @return true, if successful
     */
    boolean exists(Class<? extends Entity> entityType, Long id);

    /**
     * Checks if is active.
     *
     * @param entityType
     *            the entity type
     * @param id
     *            the id
     * @return true, if is active
     */
    boolean isActive(Class<? extends Entity> entityType, Long id);

    /**
     * Persist.
     *
     * @param entity
     *            the entity
     */
    void persist(Entity entity);

    /**
     * Refresf.
     *
     * @param entity
     *            the entity
     */
    void refresh(Entity entity);

    /**
     * Removes the.
     *
     * @param entity
     *            the entity
     */
    void remove(Entity entity);

    /**
     * Find by id.
     *
     * @param <E>
     *            the element type
     * @param <K>
     *            the key type
     * @param entityType
     *            the entity type
     * @param id
     *            the id
     * @return the e
     */
    <E extends Entity, K> E findById(Class<E> entityType, K id);

    /**
     * Find by property.
     *
     * @param <E>
     *            the element type
     * @param entityType
     *            the entity type
     * @param propertyName
     *            the property name
     * @param value
     *            the value
     * @return the list
     */
    <E extends Entity> List<E> findByProperty(Class<E> entityType, String propertyName, Object value);

    /**
     * Find by property.
     *
     * @param <E>
     *            the element type
     * @param entityType
     *            the entity type
     * @param propertyName
     *            the property name
     * @param value
     *            the value
     * @param offsetLimit
     *            the offset
     * @return the list
     */
    <E extends Entity> List<E> findByProperty(Class<E> entityType, String propertyName, Object value,
                                              OffsetLimit offsetLimit);

    /**
     * Find single result by property.
     *
     * @param <E>
     *            the element type
     * @param entityType
     *            the entity type
     * @param propertyName
     *            the property name
     * @param value
     *            the value
     * @return the e
     */
    <E> E findSingleResultByProperty(Class<E> entityType, String propertyName, Object value);

    /**
     * Gets the single result.
     *
     * @param <E>
     *            the element type
     * @param <K>
     *            the key type
     * @param entityType
     *            the entity type
     * @return the single result
     */
    <E extends Entity, K> E getSingleResult(Class<E> entityType);

    <E extends Entity> List<E> getAll(Class<E> entityType);

    /**
     * Merge.
     *
     * @param entity
     *            the entity
     * @return the entity
     */
    Entity merge(Entity entity);

    /**
     * Flush.
     */
    void flush();

    /**
     * Creates the typed query.
     *
     * @param <T>
     *            the generic type
     * @param sqlString
     *            the sql string
     * @param resultClass
     *            the result class
     * @return the typed query
     */
    <T> TypedQuery<T> createTypedQuery(String sqlString, Class<T> resultClass);

    /**
     * Creates the query.
     *
     * @param sqlString
     *            the sql string
     * @return the query
     */
    Query createQuery(String sqlString);

    /**
     * Creates the native query.
     *
     * @param <T>
     *            the generic type
     * @param sqlString
     *            the sql string
     * @param resultClass
     *            the result class
     * @return the query
     */
    <T> Query createNativeQuery(String sqlString, Class<T> resultClass);

    /**
     * Gets the reference.
     *
     * @param <E>
     *            the element type
     * @param <K>
     *            the key type
     * @param entityType
     *            the entity type
     * @param id
     *            the id
     * @return the reference
     */
    <E extends Entity, K> E getReference(Class<E> entityType, K id);

    /**
     * Detach.
     *
     * @param e
     *            the e
     */
    void detach(Entity e);

    /**
     * Gets the range.
     *
     * @param <E>
     *            the element type
     * @param entityType
     *            the entity type
     * @param offsetLimit
     *            the offset
     * @param sortFieldName
     *            the sort field name
     * @param direction
     *            the direction
     * @return the range
     */
    <E extends Entity> List<E> getRange(Class<E> entityType, OffsetLimit offsetLimit, String sortFieldName,
                                        Enums.SortDirection direction);

    /**
     * Find by restriction.
     *
     * @param <T>
     *            the generic type
     * @param entityType
     *            the entity type
     * @param restriction
     *            the restriction
     * @return the typed query
     */
    <T extends Entity> TypedQuery<T> findByRestriction(Class<T> entityType, IRestriction restriction);

    /**
     * Find by restriction.
     *
     * @param <T>
     *            the generic type
     * @param entityType
     *            the entity type
     * @param restriction
     *            the restriction
     * @param offsetLimit
     *            the offset limit
     * @return the list
     */
    <T extends Entity> List<T> findByRestriction(Class<T> entityType, IRestriction restriction,
                                                 OffsetLimit offsetLimit);

    /**
     * Count.
     *
     * @param entityType
     *            the entity type
     * @return the long
     */
    long count(Class<? extends Entity> entityType);

    /**
     * Find last.
     *
     * @param <E>
     *            the element type
     * @param entityType
     *            the entity type
     * @return the e
     */
    <E extends Entity> E findLast(Class<E> entityType);
}


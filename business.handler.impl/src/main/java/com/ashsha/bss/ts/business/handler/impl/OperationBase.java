package com.ashsha.bss.ts.business.handler.impl;

import com.ashsah.bss.ts.business.handler.IOperationBase;
import com.ashsah.bss.ts.business.handler.shared.GetRangeRequest;
import com.ashsha.bss.ts.common.TsException;
import com.ashsha.bss.ts.entity.converters.IEntityConverter;
import com.ashsha.bss.ts.entity.db.core.Entity;
import com.ashsha.bss.ts.entity.db.service.IDAO;
import com.ashsha.bss.ts.entity.db.service.query.OffsetLimit;
import com.ashsha.bss.ts.entity.dto.QueryDTO;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public abstract class OperationBase implements IOperationBase
{
    @Autowired
    private IDAO dao;

    @Autowired
    private IEntityConverter entityConverter;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
    {
        this.applicationContext = applicationContext;
    }

    protected Class<? extends Entity> getEntityType()
    {
        return Entity.class;
    }

    @Override
    public Entity delete(Long id)
    {
        return delete(getEntityType(), id);
    }

    @Override
    public <T extends Entity> Entity delete(Class<T> entityType, Long id)
    {
        Entity entity = getDao().findById(entityType, id);
        if (entity == null)
        {
            throw new TsException("ENTITY_NOT_FOUND");
        }
        getDao().remove(entity);
        return entity;
    }

    @Override
    public void delete(Entity entity)
    {
        getDao().remove(entity);
    }

    @Override
    public <E extends Entity> List<E> findByProperty(Class<E> entityType, String propertyName, Object value)
    {
        return getDao().findByProperty(entityType, propertyName, value);
    }

    @Override
    public boolean exists(Long id)
    {
        return getDao().exists(getEntityType(), id);
    }

    @Override
    public boolean isActive(Long id)
    {
        return getDao().isActive(getEntityType(), id);
    }

    @Override
    public <T extends Entity> T getById(Class<T> entityType, Long id)
    {
        return getDao().findById(entityType, id);
    }

    public <T extends Entity> T getById(Long id)
    {
        return (T) getDao().findById(getEntityType(), id);
    }

    public IDAO getDao()
    {
        return dao;
    }

    protected <F, T> T convert(Class<? extends T> toType, F toBeConverted)
    {
        return getEntityConverter().convert(toType, toBeConverted);
    }

    public IEntityConverter getEntityConverter()
    {
        return entityConverter;
    }

    public List<? extends Entity> getRange(GetRangeRequest range)
    {
        List<? extends Entity> result = (List<? extends Entity>) getDao().getRange(getEntityType(),
                new OffsetLimit(range.getFirstResult(), range.getMaxResult()), range.getSortField(), range.getDirection());
        return result;
    }

    @Override
    public <T extends Entity> T getReference(Class<T> entityType, Long biId)
    {
        return getDao().getReference(entityType, biId);
    }

    public <T> T getServiceType(Class<T> serviceClass) throws TsException
    {
        try
        {
            T instance = applicationContext.getBean(serviceClass);
            return instance;
        }
        catch (Exception exp)
        {
            TsException tsException = new TsException(exp);
            throw tsException;
        }
    }

    protected Date getUTCDate()
    {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        Calendar calendar = Calendar.getInstance(timeZone);
        Date date = calendar.getTime();

        return date;
    }

    /**
     * [DE20160729] Set provided Map of parameters to the input TypedQuery
     * instance.
     *
     * @param query
     *            TypedQuery instance coming from getDAO().createXXXQuery.
     * @param parameters
     *            Map of parameter name to parameter value.
     * @return incoming TypedQuery instance to immediately call getResultList()
     *         method.
     */
    protected <T> TypedQuery<T> mapParameters(TypedQuery<T> query, Map<String, Object> parameters)
    {
        for (Map.Entry<String, Object> parameter : parameters.entrySet())
        {
            query.setParameter(parameter.getKey(), parameter.getValue());
        }
        return query;
    }

    public void persist(Entity entity)
    {
        getDao().persist(entity);
    }

    protected <T> List<T> query(Class<T> classType, String query, Map<String, Object> parameters, QueryDTO queryDTO)
    {
        TypedQuery<T> typedQuery = mapParameters(getDao().createTypedQuery(query, classType), parameters);
        typedQuery = setContentRange(typedQuery, queryDTO);
        List<T> list = typedQuery.getResultList();
        return list;
    }

    protected <T> TypedQuery<T> setContentRange(TypedQuery<T> typedQuery, QueryDTO queryDTO)
    {
        typedQuery.setFirstResult(queryDTO.getContentRange().getFirstResult());
        typedQuery.setMaxResults(queryDTO.getContentRange().getMaxResult());
        return typedQuery;
    }
}

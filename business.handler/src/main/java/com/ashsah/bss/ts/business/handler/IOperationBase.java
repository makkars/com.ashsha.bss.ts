package com.ashsah.bss.ts.business.handler;

import com.ashsah.bss.ts.business.handler.shared.GetRangeRequest;
import com.ashsha.bss.ts.entity.db.core.Entity;
import java.util.List;
import org.springframework.context.ApplicationContextAware;

public interface IOperationBase extends ApplicationContextAware
{
    boolean exists(Long id);

    <T extends Entity> T getById(Class<T> entityType, Long id);

    <T extends Entity> T getById(Long id);

    List<? extends Entity> getRange(GetRangeRequest request);

    <T extends Entity> T getReference(Class<T> entityType, Long biId);

    <E extends Entity> List<E> findByProperty(Class<E> entityType, String propertyName, Object value);

    Entity delete(Long id);

    <T extends Entity> Entity delete(Class<T> entityType, Long id);

    void delete(Entity entity);

    boolean isActive(Long id);

    void persist(Entity entity);
}

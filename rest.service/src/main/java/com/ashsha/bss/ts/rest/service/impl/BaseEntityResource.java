package com.ashsha.bss.ts.rest.service.impl;

import com.ashsha.bss.ts.entity.converters.IEntityConverter;
import com.ashsha.bss.ts.entity.db.core.Entity;
import com.ashsha.bss.ts.entity.dto.QueryDTO;
import com.ashsha.bss.ts.rest.service.IEntityResource;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseEntityResource<E extends Entity, Q extends QueryDTO> implements IEntityResource
{
    @Autowired
    private IEntityConverter entityConverter;

    public IEntityConverter getEntityConverter()
    {
        return entityConverter;
    }
}

package com.ashsha.bss.ts.entity.converters;

import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class EntityConverter implements IEntityConverter
{
    @Autowired
    private DozerBeanMapper beanMapper;

    public DozerBeanMapper getBeanMapper()
    {
        return beanMapper;
    }

    @Override public <F, T> T convert(Class<? extends T> typeTo, F value)
    {
        return getBeanMapper().map(value, typeTo);
    }

    @Override public <F, T> List<T> convert(Class<T> typeTo, List<F> value)
    {
        List<T> toList = new ArrayList<T>();

        if (value == null || value.isEmpty())
        {
            return toList;
        }

        for (F from : value)
        {
            toList.add(convert(typeTo, from));
        }

        return toList;
    }
}

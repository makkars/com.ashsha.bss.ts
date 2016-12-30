package com.ashsha.bss.ts.entity.db.service.impl;

import java.util.List;

public interface IEntityConverter
{

    <F, T> T convert(Class<? extends T> typeTo, F value);

    <F, T> List<T> convert(Class<T> typeTo, List<F> value);

}

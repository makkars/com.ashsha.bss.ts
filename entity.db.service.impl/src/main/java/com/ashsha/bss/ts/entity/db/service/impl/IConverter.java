package com.ashsha.bss.ts.entity.db.service.impl;

public interface IConverter<F, T>
{
    T convert(F from);
}

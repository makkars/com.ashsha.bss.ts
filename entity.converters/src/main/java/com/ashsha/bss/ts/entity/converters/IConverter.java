package com.ashsha.bss.ts.entity.converters;

public interface IConverter<F, T>
{
    T convert(F from);
}

package com.ashsha.bss.ts.entity.dto;

public enum SortDirectionDTO
{
    ASC(0), DESC(1);

    private final int id;

    SortDirectionDTO(int value)
    {
        id = value;
    }

    public int getId()
    {
        return id;
    }
}

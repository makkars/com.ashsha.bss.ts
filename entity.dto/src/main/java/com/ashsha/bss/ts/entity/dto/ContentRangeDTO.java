package com.ashsha.bss.ts.entity.dto;

public class ContentRangeDTO
{
    private int start;
    private int end;
    private String sortField;
    private SortDirectionDTO direction;

    public int getFirstResult()
    {
        return this.start - 1;
    }

    public int getMaxResult()
    {
        return end - start + 1;
    }

    public void setStart(int start)
    {
        this.start = start;
    }

    public void setEnd(int end)
    {
        this.end = end;
    }

    public String getSortField()
    {
        return sortField;
    }

    public void setSortField(String sortField)
    {
        this.sortField = sortField;
    }

    public SortDirectionDTO getDirection()
    {
        return direction;
    }

    public void setDirection(SortDirectionDTO direction)
    {
        this.direction = direction;
    }
}

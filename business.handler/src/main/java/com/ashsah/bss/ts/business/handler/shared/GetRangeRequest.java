package com.ashsah.bss.ts.business.handler.shared;

import com.ashsha.bss.ts.entity.db.core.Enums;

public class GetRangeRequest
{

    private int start;
    private int end;
    private String sortField;
    private Enums.SortDirection direction;

    public GetRangeRequest(int start, int end)
    {
        this.start = start;
        this.end = end;
    }

    /**
     * [DE20161015] Constructor for sorted Range listing.
     * @param start int value starting from 1
     * @param end int value ending at Integer.MAX_VALUE
     * @param sortField Camel case field name as seen on Entity class field declaration.
     * @param direction Direction of the sort field {@link Enums.SortDirection}
     */
    public GetRangeRequest(int start, int end, String sortField, Enums.SortDirection direction)
    {
        this(start, end);
        this.sortField = sortField;
        this.direction = direction;
    }

    public int getFirstResult()
    {
        return this.start - 1;
    }

    public int getMaxResult()
    {
        return end - start + 1;
    }

    public String getSortField()
    {
        return sortField;
    }

    public void setSortField(String sortField)
    {
        this.sortField = sortField;
    }

    public Enums.SortDirection getDirection()
    {
        return direction;
    }

    public void setDirection(Enums.SortDirection direction)
    {
        this.direction = direction;
    }
}

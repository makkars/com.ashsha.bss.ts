package com.ashsha.bss.ts.entity.db.service.query;

public class OffsetLimit
{
    private final static OffsetLimit singleLimitOffset = new OffsetLimit(0, 1);

    private int offset;
    private int limit;

    public OffsetLimit(int first, int take)
    {
        offset = first;
        limit = take;
    }

    public int getOffset()
    {
        return offset;
    }

    public void setOffset(int offset)
    {
        this.offset = offset;
    }

    public int getLimit()
    {
        return limit;
    }

    public void setLimit(int limit)
    {
        this.limit = limit;
    }

    public static final OffsetLimit getSingleLimitOffset()
    {
        return singleLimitOffset;
    }
}

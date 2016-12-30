package com.ashsha.bss.ts.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (value = { "resourceName" })
public abstract class BaseDTO implements IBaseDTO
{
    private final static String API = "/v1/";
    private String href;

    public String getHref()
    {
        return href;
    }

    public void setHref(String href)
    {
        this.href = href;
    }

    public String getResourceName()
    {
        return "";
    }

    protected void setHrefInternal(Long id)
    {
        if (id == null)
        {
            this.href = null;
        }
        else if (getResourceName() != null && !getResourceName().equals(""))
        {
            String newHref = new StringBuilder(API).append(getResourceName()).append("/").append(id.toString()).toString();
            this.href = newHref;
        }
    }

}

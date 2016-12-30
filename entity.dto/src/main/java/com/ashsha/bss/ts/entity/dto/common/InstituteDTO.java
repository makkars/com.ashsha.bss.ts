package com.ashsha.bss.ts.entity.dto.common;

import com.ashsha.bss.ts.entity.dto.BaseDTO;
import com.ashsha.bss.ts.entity.dto.IInstituteDTO;

public class InstituteDTO extends BaseDTO implements IInstituteDTO
{
    private Long id;
    private String name;
    private String adminName;
    private Long adminContactPrimary;
    private Long adminContactSecondary;
    private String appName;
    private String branches;
    private String imagesLocationOnServer;

    @Override public void setId(Long id)
    {
        this.id = id;
    }

    @Override public void setName(String name)
    {
        this.name = name;
    }

    @Override public void setAdminName(String adminName)
    {
        this.adminName = adminName;
    }

    @Override public void setAdminContactPrimary(Long adminContactPrimary)
    {
        this.adminContactPrimary = adminContactPrimary;
    }

    @Override public void setAdminContactSecondary(Long adminContactSecondary)
    {
        this.adminContactSecondary = adminContactSecondary;
    }

    @Override public void setAppName(String appName)
    {
        this.appName = appName;
    }

    @Override public void setBranches(String branches)
    {
        this.branches = branches;
    }

    @Override public void setImagesLocationOnServer(String imagesLocationOnServer)
    {
        this.imagesLocationOnServer = imagesLocationOnServer;
    }

    @Override public Long getId()
    {
        return id;
    }

    @Override public String getName()
    {
        return name;
    }

    @Override public String getAdminName()
    {
        return adminName;
    }

    @Override public Long getAdminContactPrimary()
    {
        return adminContactPrimary;
    }

    @Override public Long getAdminContactSecondary()
    {
        return adminContactSecondary;
    }

    @Override public String getAppName()
    {
        return appName;
    }

    @Override public String getBranches()
    {
        return branches;
    }

    @Override public String getImagesLocationOnServer()
    {
        return imagesLocationOnServer;
    }
}

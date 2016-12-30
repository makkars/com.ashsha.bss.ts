package com.ashsha.bss.ts.entity.dto;

public interface IInstituteDTO
{
    Long getId();

    String getName();

    Long getAdminContactPrimary();

    Long getAdminContactSecondary();

    String getBranches();

    String getAppName();

    String getImagesLocationOnServer();

    String getAdminName();

    void setId(Long id);

    void setName(String name);

    void setAdminContactPrimary(Long adminContactPrimary);

    void setAdminContactSecondary(Long adminContactSecondary);

    void setBranches(String branches);

    void setAppName(String appName);

    void setImagesLocationOnServer(String imagesLocationOnServer);

    void setAdminName(String adminName);

}

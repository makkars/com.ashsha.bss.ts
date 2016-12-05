package com.ashsha.bss.ts.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table (name = "institute")
public class Institute
{
    @Id
    @GeneratedValue (generator = "increment")
    @GenericGenerator (name = "increment", strategy = "increment")
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "admin_name")
    private String adminName;

    @Column (name = "admin_contact_primary")
    private Long adminContactPrimary;

    @Column (name = "admin_contact_secondary")
    private Long adminContactSecondary;

    @Column (name = "app_name")
    private String appName;

    @ElementCollection (targetClass = String.class)
    @Column (name = "branches")
    private List<String> branches;

    @ElementCollection (targetClass = String.class)
    @Column (name = "images")
    private List<String> imagesLocationOnServer;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getAdminContactPrimary()
    {
        return adminContactPrimary;
    }

    public void setAdminContactPrimary(Long adminContactPrimary)
    {
        this.adminContactPrimary = adminContactPrimary;
    }

    public Long getAdminContactSecondary()
    {
        return adminContactSecondary;
    }

    public void setAdminContactSecondary(Long adminContactSecondary)
    {
        this.adminContactSecondary = adminContactSecondary;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<String> getBranches()
    {
        return branches;
    }

    public void setBranches(List<String> branches)
    {
        this.branches = branches;
    }

    public String getAppName()
    {
        return appName;
    }

    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    public List<String> getImagesLocationOnServer()
    {
        return imagesLocationOnServer;
    }

    public void setImagesLocationOnServer(List<String> imagesLocationOnServer)
    {
        this.imagesLocationOnServer = imagesLocationOnServer;
    }

    public String getAdminName()
    {
        return adminName;
    }

    public void setAdminName(String adminName)
    {
        this.adminName = adminName;
    }

    @Override public String toString()
    {
        final StringBuilder sb = new StringBuilder("Institute{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", adminName='").append(adminName).append('\'');
        sb.append(", adminContactPrimary=").append(adminContactPrimary);
        sb.append(", adminContactSecondary=").append(adminContactSecondary);
        sb.append(", appName='").append(appName).append('\'');
        sb.append(", branches=").append(branches);
        sb.append(", imagesLocationOnServer=").append(imagesLocationOnServer);
        sb.append('}');
        return sb.toString();
    }
}

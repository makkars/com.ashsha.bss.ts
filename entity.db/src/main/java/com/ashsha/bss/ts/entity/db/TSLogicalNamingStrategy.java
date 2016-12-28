package com.ashsha.bss.ts.entity.db;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitBasicColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitForeignKeyNameSource;
import org.hibernate.boot.model.naming.ImplicitIdentifierColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitJoinColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl;
import org.hibernate.boot.model.source.spi.AttributePath;

public class TSLogicalNamingStrategy extends ImplicitNamingStrategyComponentPathImpl
{

    private static final long serialVersionUID = -7901085235970606478L;

    private final int maxColumnNameLength = 30;

    /* (non-Javadoc)
     * @see org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl#determineBasicColumnName(org.hibernate.boot.model.naming.ImplicitBasicColumnNameSource)
     */
    @Override
    public Identifier determineBasicColumnName(ImplicitBasicColumnNameSource source)
    {
        Identifier result;
        AttributePath path = source.getAttributePath();
        String fullPath = path.getFullPath();
        if (fullPath.contains(".") && fullPath.length() > maxColumnNameLength)
        {
            String columnName = getColumnName(fullPath, "\\.");
            result = toIdentifier(columnName, source.getBuildingContext());
        }
        else
        {
            result = super.determineBasicColumnName(source);
        }
        return result;
    }

    private String getColumnName(String fullPath, String regexSplitter)
    {
        String[] segments = fullPath.split(regexSplitter);
        String columnName = "";
        for (int i = 0; i < segments.length; i++)
        {
            if (i == segments.length - 1)
            {

                columnName += segments[i];
                break;
            }
            columnName += segments[i].substring(0, 1).toUpperCase();
            for (int j = 1; j < segments[i].length(); j++)
            {
                char c = segments[i].charAt(j);
                columnName += Character.isUpperCase(c) ? c : "";
            }
            columnName += "_";
        }
        return columnName;
    }

    /* (non-Javadoc)
     * @see org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl#determineIdentifierColumnName(org.hibernate.boot.model.naming.ImplicitIdentifierColumnNameSource)
     */
    @Override
    public Identifier determineIdentifierColumnName(ImplicitIdentifierColumnNameSource source)
    {
        Identifier result;
        Identifier generatedIdentifier = super.determineIdentifierColumnName(source);

        String fkPath = generatedIdentifier.getCanonicalName();
        if (fkPath.length() > maxColumnNameLength)
        {
            String columnName = getColumnName(fkPath, "_");
            result = toIdentifier(columnName, source.getBuildingContext());
        }
        else
        {
            result = super.determineIdentifierColumnName(source);
        }
        return result;
    }

    /* (non-Javadoc)
     * @see org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl#determineJoinColumnName(org.hibernate.boot.model.naming.ImplicitJoinColumnNameSource)
     */
    @Override
    public Identifier determineJoinColumnName(ImplicitJoinColumnNameSource source)
    {
        Identifier result;
        Identifier generatedIdentifier = super.determineJoinColumnName(source);

        String fkPath = generatedIdentifier.getCanonicalName();
        if (fkPath.length() > maxColumnNameLength)
        {
            String columnName = getColumnName(fkPath, "_");
            result = toIdentifier(columnName, source.getBuildingContext());
        }
        else
        {
            result = super.determineJoinColumnName(source);
        }
        return result;
    }

    /* (non-Javadoc)
     * @see org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl#determineForeignKeyName(org.hibernate.boot.model.naming.ImplicitForeignKeyNameSource)
     */
    @Override
    public Identifier determineForeignKeyName(ImplicitForeignKeyNameSource source)
    {
        Identifier result;
        String fkPath = source.getColumnNames().get(0).getCanonicalName();
        if (fkPath.length() > maxColumnNameLength)
        {
            String columnName = getColumnName(fkPath, "_");
            result = toIdentifier(columnName, source.getBuildingContext());
        }
        else
        {
            result = super.determineForeignKeyName(source);
        }
        return result;
    }

}

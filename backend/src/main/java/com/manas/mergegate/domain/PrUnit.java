package com.manas.mergegate.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PrUnit {
    @Id
    private String prId;
    private String className;
    private String packageName;
    private String source;
    private String description;

    protected PrUnit() { }

    public PrUnit(String prId, String className, String packageName, String source, String description) {
        this.prId = prId;
        this.className = className;
        this.packageName = packageName;
        this.source = source;
        this.description = description;
    }

    public String getPrId() { return prId; }
    public String getClassName() { return className; }
    public String getPackageName() { return packageName; }
    public String getSource() { return source; }
    public String getDescription() { return description; }
}

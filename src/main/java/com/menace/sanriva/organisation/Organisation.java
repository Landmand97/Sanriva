package com.menace.sanriva.organisation;

import jakarta.annotation.Nullable;

public class Organisation {

    private final String name;
    private final String type;
    private final String region;
    private final String specialization;
    private final Long cvr;
    private final Long sorCode;
    private final Long parentSorCode;

    public Organisation(String name, String type, String region, String specialization, Long cvr, Long sorCode, Long parentSorCode) {
        this.name = name;
        this.type = type;
        this.region = region;
        this.specialization = specialization;
        this.cvr = cvr;
        this.sorCode = sorCode;
        this.parentSorCode = parentSorCode;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getRegion() {
        return region;
    }

    public String getSpecialization() {
        return specialization;
    }

    public Long getSorCode() {
        return sorCode;
    }

    public Long getCvr() {
        return cvr;
    }

    public @Nullable Long getParentSorCode() {
        return parentSorCode;
    }

}

package com.prueba.administradortarea.specifications.impl;

import com.prueba.administradortarea.specifications.SqlSpecification;

import java.util.Objects;

public class FindAllSpecification implements SqlSpecification {

    private Class tClass;

    public FindAllSpecification(Class tClass) {
        Objects.requireNonNull(tClass, "tClass is required");
        this.tClass = tClass;
    }

    @Override
    public String toSqlQuery() {
        return String.format("Select a from %s a where a is not NULL", tClass.getName());
    }
}


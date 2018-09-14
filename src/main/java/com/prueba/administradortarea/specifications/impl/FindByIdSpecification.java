package com.prueba.administradortarea.specifications.impl;

import com.prueba.administradortarea.specifications.SqlSpecification;

import java.util.Objects;

public class FindByIdSpecification<T> implements SqlSpecification {

    private T id;
    private Class tclass;

    public FindByIdSpecification(T id, Class tClass) {
        Objects.requireNonNull(id, "id is required");
        Objects.requireNonNull(tClass, "tClass is required");
        this.id = id;
        this.tclass = tClass;
    }

    @Override
    public String toSqlQuery() {
        return String.format("Select a from %s a where a.id=%s", tclass.getName(), id.toString());
    }
}


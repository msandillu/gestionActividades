package com.prueba.administradortarea.specifications.impl;

import com.prueba.administradortarea.specifications.SqlSpecification;

import java.util.Objects;

public class RemoveByIdSpecification implements SqlSpecification {

    Long id;
    Class tClass;

    public RemoveByIdSpecification(Long id, Class tClass) {
        Objects.requireNonNull(id, "id is required");
        Objects.requireNonNull(tClass, "tClass is required");
        this.id = id;
        this.tClass = tClass;
    }

    @Override
    public String toSqlQuery() {
        return String.format("delete from %s a where a.id = %d", tClass.getName(), id);
    }
}

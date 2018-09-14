package com.prueba.administradortarea.repositories;

import com.prueba.administradortarea.specifications.Specification;

import java.util.List;

public interface DataAccessRepository<T> {

    T get(Long id);
    List<T> find(Specification specification);

}

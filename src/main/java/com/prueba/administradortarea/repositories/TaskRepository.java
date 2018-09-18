package com.prueba.administradortarea.repositories;

import com.prueba.administradortarea.models.domain.Task;

import java.util.List;

public interface TaskRepository {

    Task add(Task task);
    void remove(Task task);
    Task findById(Integer taskId);
    List<Task> findAll();
    /*void removeById(Long id, Class tClass);
    void update(Object object);*/

}

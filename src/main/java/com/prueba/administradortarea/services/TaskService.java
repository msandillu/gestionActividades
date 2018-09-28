package com.prueba.administradortarea.services;

import com.prueba.administradortarea.models.request.TaskRequest;
import com.prueba.administradortarea.models.response.TaskResponse;

import java.util.Collection;


public interface TaskService{

    Integer createTask(TaskRequest taskRequest);

    Collection<TaskResponse> getTask();

    TaskResponse findTask(Integer id);

    Boolean deleteTask(Integer id);

   /*
    public Task editTask(Task forEdit);

    public void deleteTask(Integer id);*/
}

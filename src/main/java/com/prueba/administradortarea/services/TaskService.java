package com.prueba.administradortarea.services;

import com.prueba.administradortarea.models.request.TaskRequest;
import com.prueba.administradortarea.models.response.TaskResponse;

import java.util.Collection;


public interface TaskService{

    public TaskResponse createTask(TaskRequest taskRequest);

    public Collection<TaskResponse> getTask();

    public TaskResponse findTask(Integer id);

   /*
    public Task editTask(Task forEdit);

    public void deleteTask(Integer id);*/
}

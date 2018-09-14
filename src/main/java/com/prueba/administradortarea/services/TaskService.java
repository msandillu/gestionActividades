package com.prueba.administradortarea.services;

import com.prueba.administradortarea.models.request.TaskRequest;
import com.prueba.administradortarea.models.response.TaskResponse;


public interface TaskService{

    public Integer createTask(TaskRequest taskRequest);

    public TaskResponse findTask(Integer id);

   /* public Collection<Task> getTask();

    public Task editTask(Task forEdit);

    public void deleteTask(Integer id);*/
}

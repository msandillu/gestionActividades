package com.prueba.administradortarea.services;

import com.prueba.administradortarea.models.domain.Task;
import com.prueba.administradortarea.models.request.TaskRequest;
import com.prueba.administradortarea.models.response.TaskResponse;
import com.prueba.administradortarea.repositories.DataPersistenceRepository;
import com.prueba.administradortarea.repositories.InternalDataRepository;

import java.util.Date;
import java.util.HashMap;


public class TaskServiceImpl implements TaskService {

    //private DataPersistenceRepository bufferTaskRepository;
    private HashMap<Integer, Task> taskMap;

    public TaskServiceImpl() {
        taskMap = new HashMap<>();
    }

    /*public TaskServiceImpl(DataPersistenceRepository bufferTaskRepository) {
        this.bufferTaskRepository = bufferTaskRepository;
    }*/

    @Override
    public Integer createTask(TaskRequest taskRequest) {
        Task task = new Task(taskRequest.getName(), taskRequest.getDescription(), 2020, new Date());
        //bufferTaskRepository.add(task);
        taskMap.put(task.getId(), task);
        return task.getId();
    }

    @Override
    public TaskResponse findTask(Integer id) {
        Task task = taskMap.get(id);
        return new TaskResponse(task.getId(), task.getName(), task.getDescription(), task.getCreationUser(), task.getCreationDate());
    }

    /*@Override
    public Collection<Task> getTask() {
        return taskMap.values();
    }

    @Override
    public Task editTask(Task forEdit) {

        Task toEdit = taskMap.get(forEdit.getId());


        if (forEdit.getName() != null) {
            toEdit.setName(forEdit.getName());
        }
        if (forEdit.getDescription() != null){
            toEdit.setDescription(forEdit.getDescription());
        }
        if (forEdit.getCreationUser() != null){
            toEdit.setCreationUser(forEdit.getCreationUser());
        }
        if (forEdit.getCreationDate() != null){
            toEdit.setCreationDate(forEdit.getCreationDate());
        }
        if (forEdit.getId() != null) {
            toEdit.setId(forEdit.getId());
        }

        return toEdit;

    }

    @Override
    public void deleteTask(Integer id) {
        taskMap.remove(id);
    }*/
}

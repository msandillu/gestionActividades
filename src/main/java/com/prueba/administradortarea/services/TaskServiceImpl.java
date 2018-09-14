package com.prueba.administradortarea.services;

import com.prueba.administradortarea.models.domain.Task;
import com.prueba.administradortarea.models.request.TaskRequest;
import com.prueba.administradortarea.models.response.TaskResponse;
import com.prueba.administradortarea.repositories.DataPersistenceRepository;
import com.prueba.administradortarea.repositories.InternalDataRepository;

import java.util.*;


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
        Task task = new Task();
        task.setId(taskMap.size()+1);
        task.setName(taskRequest.getName());
        task.setDescription(taskRequest.getDescription());
        task.setCreationUser(null);
        task.setCreationDate(new Date());
        //bufferTaskRepository.add(task);
        taskMap.put(task.getId(), task);
        return task.getId();
    }

    @Override
    public Collection<TaskResponse> getTask() {
        HashMap<Integer, TaskResponse> taskResponseMap = new HashMap<>();
        Iterator it = taskMap.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry taskEntry = (Map.Entry) it.next();
            Task task = (Task) taskEntry.getValue();
            taskResponseMap.put(task.getId(),getTaskResponse(task));
        }
        return taskResponseMap.values();
    }

    @Override
    public TaskResponse findTask(Integer id) {
        Task task = taskMap.get(id);
        if (task != null){
            return getTaskResponse(task);
        } else{
            return null;
        }
    }

    private TaskResponse getTaskResponse(Task task){
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setName(task.getName());
        taskResponse.setDescription(task.getDescription());
        taskResponse.setCreationUser(task.getCreationUser());
        taskResponse.setCreationDate(task.getCreationDate());
        return taskResponse;
    }

    /*

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

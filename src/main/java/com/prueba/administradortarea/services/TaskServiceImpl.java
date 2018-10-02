package com.prueba.administradortarea.services;

import com.prueba.administradortarea.models.domain.Task;
import com.prueba.administradortarea.models.request.TaskRequest;
import com.prueba.administradortarea.models.response.TaskResponse;
import com.prueba.administradortarea.repositories.TaskRepository;

import java.util.*;


public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Integer createTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.setName(taskRequest.getName());
        task.setDescription(taskRequest.getDescription());
        task.setCreationUser(taskRequest.getIdUser());
        task.setCreationDate(new Date());
        task = taskRepository.add(task);
        return task.getId();
    }

    @Override
    public Collection<TaskResponse> getTask() {
        HashMap<Integer, TaskResponse> taskResponseMap = new HashMap<>();
        List<Task> taskList = taskRepository.findAll();
        for (Task t : taskList){
            taskResponseMap.put(t.getId(),getTaskResponse(t));
        }
        return taskResponseMap.values();
    }

    @Override
    public TaskResponse findTask(Integer id) {
        Task task = taskRepository.findById(id);
        if (task != null){
            return getTaskResponse(task);
        } else{
            return null;
        }
    }

    @Override
    public Boolean deleteTask(Integer id) {
        Task task = taskRepository.findById(id);
        if (task != null){
            return taskRepository.remove(task);
        } else{
            return false;
        }
    }

    @Override
    public TaskResponse editTask(Integer idTask, TaskRequest taskRequest) {

        Task task = taskRepository.findById(idTask);

        if (taskRequest.getName() != null) {
            task.setName(taskRequest.getName());
        }
        if (taskRequest.getDescription() != null){
            task.setDescription(taskRequest.getDescription());
        }

        return getTaskResponse(taskRepository.update(task));
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
}

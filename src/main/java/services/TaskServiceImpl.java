package services;

import model.Task;

import java.util.Collection;
import java.util.HashMap;

public class TaskServiceImpl implements  TaskService {

    private HashMap<Object, Task> taskMap;

    public TaskServiceImpl() {
        taskMap = new HashMap<Object, Task>();
    }

    @Override
    public void addTask(Task task) {
        taskMap.put(task.getId(), task);
    }

    @Override
    public Collection<Task> getTask() {
        return taskMap.values();
    }

    @Override
    public Task getTask(Integer id) {
        return taskMap.get(id);
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
    }
}

package services;

import model.Task;

import java.util.Collection;


public interface TaskService{

    public void addTask(Task task);

    public Collection<Task> getTask();

    public Task getTask(Integer id);

    public Task editTask(Task forEdit);

    public void deleteTask(Integer id);
}

package controllers;

import enumerate.StatusResponse;
import com.google.gson.Gson;
import model.StandardResponse;
import model.Task;
import services.TaskService;
import services.TaskServiceImpl;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

public class TaskController {

    public void setup (){

        final TaskService taskService = new TaskServiceImpl();

        post("/tasks", (request, response) -> {
            response.type("application/json");

            Task task = new Gson().fromJson(request.body(), Task.class);
            taskService.addTask(task);

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        get("/tasks", (request, response) -> {
            response.type("application/json");

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(taskService.getTask())));
        });

        get("/tasks/:id", (request, response) -> {
            response.type("application/json");

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(taskService.getTask(Integer.parseInt(request.params(":id"))))));
        });

        put("/tasks/:id", (request, response) -> {
            response.type("application/json");

            Task toEdit = new Gson().fromJson(request.body(), Task.class);
            Task editedTask = taskService.editTask(toEdit);

            if (editedTask != null) {
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(editedTask)));
            } else {
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, new Gson().toJson("Task not found")));
            }
        });

        delete("/tasks/:id", (request, response) -> {
            response.type("application/json");

            taskService.deleteTask(Integer.parseInt(request.params(":id")));
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "task deleted"));
        });

    }

}

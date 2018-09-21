package com.prueba.administradortarea.controllers;

import com.prueba.administradortarea.exception.ApiException;
import com.prueba.administradortarea.models.request.TaskRequest;
import com.prueba.administradortarea.models.response.TaskResponse;
import com.prueba.administradortarea.parsers.Parser;
import com.prueba.administradortarea.services.TaskService;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class TaskController {

    private final TaskService taskService;
    private final Parser parser;
    private final Validator validator;

    public TaskController(TaskService taskService, Parser parser, Validator validator) {
        this.parser = parser;
        this.taskService = taskService;
        this.validator = validator;
    }

    public Route postTasks = this::postTasks;
    public Route getTasks = this::getTasks;
    public Route getTasksById = this::getTasksById;


    private String postTasks(Request request, Response response) throws IOException {
        response.type("application/json");

        TaskRequest taskRequest = parser.parseToObject(request.body(), TaskRequest.class);

        Set<ConstraintViolation<TaskRequest>> validationResult = validator.validate(taskRequest);

        if (!validationResult.isEmpty()) {

            List<ApiException> exceptions = new ArrayList<>();
            for (ConstraintViolation cv : validationResult) {
                ApiException apiException = new ApiException();
                apiException.setDescription(cv.getMessage());
                exceptions.add(apiException);
            }

            response.status(HttpStatus.BAD_REQUEST_400);
            return parser.parseToString(exceptions);
        }

        response.status(HttpStatus.CREATED_201);
        Integer taskId = taskService.createTask(taskRequest);
        return parser.parseToString(taskService.findTask(taskId));
    }

    ;

    private String getTasks(Request request, Response response) throws IOException {
        response.type("application/json");
        response.status(HttpStatus.OK_200);
        return parser.parseToString(taskService.getTask());
    }

    ;

    private String getTasksById(Request request, Response response) throws IOException {
        response.type("application/json");

        Integer taskId = Integer.parseInt(request.params(":id"));
        TaskResponse taskResponse = null;

        if (taskId != null) {
            taskResponse = taskService.findTask(taskId);
        }
        if (taskResponse == null) {
            response.status(HttpStatus.NOT_FOUND_404);
            ApiException apiException = new ApiException();
            apiException.setDescription("Not found the Task");
            return parser.parseToString(apiException);
        }

        response.status(HttpStatus.OK_200);
        return parser.parseToString(taskResponse);
    }

    ;

   /* get("/tasks/:id", (request, response) -> {
        response.type("application/json");

        response.status(HttpStatus.OK_200);

        return new ObjectMapper().writeValueAsString(taskService.getTask(Integer.parseInt(request.params(":id"))));
    });

    put("/tasks/:id", (request, response) -> {
        response.type("application/json");

        Task toEdit = new Gson().fromJson(request.body(), Task.class);
        Task editedTask = taskService.editTask(toEdit);

        if (editedTask != null) {
            response.status(HttpStatus.OK_200);
            return new ObjectMapper().writeValueAsString(editedTask);
        } else {
            response.status(HttpStatus.NOT_FOUND_404);
            return new ObjectMapper().writeValueAsString(HttpStatus.NOT_FOUND_404);
        }
    });

    delete("/tasks/:id", (request, response) -> {
        response.type("application/json");

        taskService.deleteTask(Integer.parseInt(request.params(":id")));
        return new ObjectMapper().writeValueAsString(HttpStatus.OK_200);
    });*/


}

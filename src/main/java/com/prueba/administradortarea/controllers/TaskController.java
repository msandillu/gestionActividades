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

    private Integer userAuthenticateId = null;

    public TaskController(TaskService taskService, Parser parser, Validator validator) {
        this.parser = parser;
        this.taskService = taskService;
        this.validator = validator;
    }

    public Route postTasks = this::postTasks;
    public Route getTasks = this::getTasks;
    public Route getTasksById = this::getTasksById;
    public Route deleteTasksById = this::deleteById;
    public Route editTasksById = this::editTask;


    private String postTasks(Request request, Response response) throws IOException {
        response.type("application/json");

        TaskRequest taskRequest = parser.parseToObject(request.body(), TaskRequest.class);
        taskRequest.setIdUser(getUserAuthenticateId());

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


    private String getTasks(Request request, Response response) throws IOException {
        response.type("application/json");
        response.status(HttpStatus.OK_200);
        return parser.parseToString(taskService.getTask());
    }


    private String getTasksById(Request request, Response response) throws IOException {
        response.type("application/json");

        Integer taskId = parser.parseToInteger(request.params(":id"));
        TaskResponse taskResponse = null;

        if (taskId != null) {
            taskResponse = taskService.findTask(taskId);
        } else{
            response.status(HttpStatus.BAD_REQUEST_400);
            ApiException apiException = new ApiException();
            apiException.setDescription("The request is incorrect");
            return parser.parseToString(apiException);
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

    private String deleteById(Request request, Response response) throws IOException{
        response.type("application/json");

        Integer taskId = parser.parseToInteger(request.params(":id"));
        Boolean deleted;

        if (taskId == null) {
            response.status(HttpStatus.BAD_REQUEST_400);
            ApiException apiException = new ApiException();
            apiException.setDescription("The request is incorrect");
            return parser.parseToString(apiException);
        }

        if (!taskService.deleteTask(taskId)) {
            response.status(HttpStatus.NOT_FOUND_404);
            ApiException apiException = new ApiException();
            apiException.setDescription("Not found the Task");
            return parser.parseToString(apiException);
        }

        response.status(HttpStatus.OK_200);
        return "";
    }

    private String editTask(Request request, Response response) throws IOException{
        response.type("application/json");

        Integer taskId = parser.parseToInteger(request.params(":id"));
        TaskRequest taskRequest = parser.parseToObject(request.body(), TaskRequest.class);

        if (taskId == null || taskRequest == null){
            response.status(HttpStatus.BAD_REQUEST_400);
            ApiException apiException = new ApiException();
            apiException.setDescription("The request is incorrect");
            return parser.parseToString(apiException);
        }

        TaskResponse taskResponse = taskService.editTask(taskId, taskRequest);
        response.status(HttpStatus.OK_200);

        return parser.parseToString(taskResponse);
    }

    public Integer getUserAuthenticateId() {
        return userAuthenticateId;
    }

    public void setUserAuthenticateId(Integer userAuthenticateId) {
        this.userAuthenticateId = userAuthenticateId;
    }

}

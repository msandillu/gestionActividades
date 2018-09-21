package com.prueba.administradortarea.controllers;

import com.prueba.administradortarea.models.request.TaskRequest;
import com.prueba.administradortarea.parsers.Parser;
import com.prueba.administradortarea.services.TaskService;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validator;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TaskControllerCreateTest {

    private TaskController taskController;
    private TaskService taskService;
    private Parser parser;
    private Validator validator;
    private Request request;
    private Response response;
    private TaskRequest taskRequest;

    private final String parsedResponse = "parsedCreatedTaskResponse";


    @Before
    public void setup(){
        taskService = mock(TaskService.class);
        parser = mock(Parser.class);
        validator = mock(Validator.class);
        taskController = new TaskController(taskService, parser, validator);

        request = mock(Request.class);
        response = mock(Response.class);

        when(request.body()).thenReturn("");
    }

    @Test
    public void createTaskShouldReturnCreatedRequestWhenValidatorIsOk() throws Exception {

        setupCreateTaskTestSuccess();

        taskController.postTasks.handle(request, response);

        verify(response, times(1)).status(HttpStatus.CREATED_201);
    }

    @Test
    public void createTaskShouldCallServiceAndReturnedTaskResponse() throws Exception {

        setupCreateTaskTestSuccess();

        String result = (String) taskController.postTasks.handle(request, response);
        verify(taskService, times(1)).createTask(taskRequest);

        assertNotNull(result);
        assertEquals(parsedResponse, result);
    }

    @Test
    public void createTaskShouldReturnBadRequestWhenValidatorReturnError() throws Exception {

        setupCreateTaskTestFail();

        taskController.postTasks.handle(request, response);

        verify(response, times(1)).status(HttpStatus.BAD_REQUEST_400);
    }

    @Test
    public void createTaskNotShouldCallCreateTaskServiceWhenValidatorReturnsError() throws Exception {
        setupCreateTaskTestFail();

        taskController.postTasks.handle(request, response);

        verify(taskService, never()).createTask(any(TaskRequest.class));
    }

    private void setupCreateTaskTestSuccess() throws IOException {
        Set<ConstraintViolation<TaskRequest>> errors = new HashSet<>();

        taskRequest = new TaskRequest();
        when(parser.parseToObject(anyString(), eq(TaskRequest.class))).thenReturn(taskRequest);
        when(validator.validate(taskRequest)).thenReturn(errors);

        when(taskService.createTask(any(TaskRequest.class))).thenReturn(1);
        when(parser.parseToString(any())).thenReturn(parsedResponse);
    }

    private void setupCreateTaskTestFail() throws IOException {
        final String errorMessage = "error message";
        final String propertyName = "prop name";
        Set<ConstraintViolation<TaskRequest>> errors = new HashSet<>();
        ConstraintViolation error = mock(ConstraintViolation.class);
        Path path = mock(Path.class);
        when(path.toString()).thenReturn(propertyName);
        when(error.getPropertyPath()).thenReturn(path);
        when(error.getMessage()).thenReturn(errorMessage);
        errors.add(error);

        taskRequest = new TaskRequest();
        when(parser.parseToObject(anyString(), eq(TaskRequest.class))).thenReturn(taskRequest);
        when(validator.validate(taskRequest)).thenReturn(errors);
    }

}

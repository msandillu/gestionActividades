package com.prueba.administradortarea.controllers;

import com.prueba.administradortarea.models.response.TaskResponse;
import com.prueba.administradortarea.parsers.Parser;
import com.prueba.administradortarea.services.TaskService;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;

import static org.mockito.Mockito.*;
import javax.validation.Validator;

public class TaskControllerFindByIdTest {

    private TaskController taskController;
    private TaskService taskService;
    private Parser parser;
    private Request request;
    private Response response;
    private TaskResponse taskResponse;
    private Validator validator;

    private final String parsedResponse = "parsedCreatedTaskResponse";

    @BeforeEach
    void setup(){
        taskController = mock(TaskController.class);
        taskService = mock(TaskService.class);
        validator = mock(Validator.class);
        parser = mock(Parser.class);

        taskController = new TaskController(taskService, parser, validator);

        request = mock(Request.class);
        response = mock(Response.class);
        taskResponse = new TaskResponse();
    }

    @Test
    void shouldReturnOkResponseWhenTaskExist() throws Exception {

        setupFindByIdTaskTestSuccess();

        taskController.getTasksById.handle(request, response);
        when(parser.parseToString(taskResponse)).thenReturn(parsedResponse);

        verify(response, times(1)).status(HttpStatus.OK_200);
    }

    @Test
    void shouldReturnNotFoundResponseWhenTaskNotExist() throws Exception {

        when(request.params(":id")).thenReturn("99");
        when(parser.parseToInteger(anyString())).thenReturn(99);
        when(taskService.findTask(anyInt())).thenReturn(null);

        taskController.getTasksById.handle(request, response);

        verify(response, times(1)).status(HttpStatus.NOT_FOUND_404);
    }

    @Test
    void shouldReturnNotFoundResponseWhenTaskIdIsNullOrIsNotInteger() throws Exception {

        setupFindByIdTaskTestFail();

        taskController.getTasksById.handle(request, response);

        verify(response, times(1)).status(HttpStatus.BAD_REQUEST_400);
    }

    @Test
    void shouldCallTaskServiceWhenTaskIdIsOk() throws Exception {

        setupFindByIdTaskTestSuccess();

        taskController.getTasksById.handle(request, response);

        verify(taskService, times(1)).findTask(anyInt());
    }

    @Test
    void notShouldCallTaskServiceWhenTaskIdIsNotOk() throws Exception {

        setupFindByIdTaskTestFail();

        taskController.getTasksById.handle(request, response);

        verify(taskService, never()).findTask(anyInt());
    }

    private void setupFindByIdTaskTestSuccess(){
        when(request.params(":id")).thenReturn("1");
        when(parser.parseToInteger(anyString())).thenReturn(1);
        when(taskService.findTask(anyInt())).thenReturn(taskResponse);
    }

    private void setupFindByIdTaskTestFail(){
        when(request.params(":id")).thenReturn("dfsdfhsd");
        when(parser.parseToInteger(anyString())).thenReturn(null);
        when(taskService.findTask(anyInt())).thenReturn(taskResponse);
    }
}

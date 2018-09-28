package com.prueba.administradortarea.controllers;

import com.prueba.administradortarea.parsers.Parser;
import com.prueba.administradortarea.services.TaskService;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;

import javax.validation.Validator;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class TaskControllerDeleteByIdTest {

    private TaskController taskController;
    private TaskService taskService;
    private Parser parser;
    private Request request;
    private Response response;
    private Validator validator;

    private final Integer idTaskValue = 1;

    @BeforeEach
    void setup(){
        taskController = mock(TaskController.class);
        taskService = mock(TaskService.class);
        validator = mock(Validator.class);
        parser = mock(Parser.class);

        taskController = new TaskController(taskService, parser, validator);

        request = mock(Request.class);
        response = mock(Response.class);
    }

    @Test
    void shouldReturnOkResponseWhenTaskDeleted() throws Exception {

        setupDeleteByIdTaskTestSuccess();

        taskController.deleteTasksById.handle(request, response);

        verify(response, times(1)).status(HttpStatus.OK_200);
    }

    @Test
    void shouldReturnNotFoundResponseWhenTaskForDeleteNotExist() throws Exception {

        when(request.params(":id")).thenReturn("99");
        when(parser.parseToInteger(anyString())).thenReturn(99);
        when(taskService.deleteTask(anyInt())).thenReturn(false);

        taskController.deleteTasksById.handle(request, response);

        verify(response, times(1)).status(HttpStatus.NOT_FOUND_404);
    }

    @Test
    void shouldReturnNotFoundResponseWhenTaskIdIsNullOrIsNotInteger() throws Exception {

        setupDeleteByIdTaskTestFail();

        taskController.deleteTasksById.handle(request, response);

        verify(response, times(1)).status(HttpStatus.BAD_REQUEST_400);
    }

    @Test
    void shouldCallTaskServiceWhenTaskIdDeleteIsOk() throws Exception {

        setupDeleteByIdTaskTestSuccess();

        taskController.deleteTasksById.handle(request, response);

        verify(taskService, times(1)).deleteTask(idTaskValue);
    }

    @Test
    void notShouldCallTaskServiceWhenTaskIdDeleteIsNotOk() throws Exception {

        setupDeleteByIdTaskTestFail();

        taskController.deleteTasksById.handle(request, response);

        verify(taskService, never()).deleteTask(anyInt());
    }

    private void setupDeleteByIdTaskTestSuccess(){
        when(request.params(":id")).thenReturn("1");
        when(parser.parseToInteger(anyString())).thenReturn(idTaskValue);
        when(taskService.deleteTask(anyInt())).thenReturn(true);
    }

    private void setupDeleteByIdTaskTestFail(){
        when(request.params(":id")).thenReturn("dfsdfhsd");
        when(parser.parseToInteger(anyString())).thenReturn(null);
        when(taskService.deleteTask(anyInt())).thenReturn(false);
    }
}

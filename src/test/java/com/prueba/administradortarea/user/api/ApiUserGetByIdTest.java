package com.prueba.administradortarea.user.api;

import com.prueba.administradortarea.helper.HttpHelper;
import com.prueba.administradortarea.models.domain.external.TokenResponse;
import com.prueba.administradortarea.models.domain.external.User;
import com.prueba.administradortarea.parsers.Parser;
import com.prueba.administradortarea.user.api.external.ApiUser;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.mockito.Mockito.*;

public class ApiUserGetByIdTest {

    private ApiUser apiUser;
    private CloseableHttpClient client;
    private CloseableHttpResponse response;
    private HttpHelper httpHelper;
    private Parser parser;
    private String urlApi;
    private String userName;
    private String password;
    private TokenResponse tokenResponse;
    private User user;
    private List<User> userList;

    private String httpResponseString = "httpResponseString";
    private String tokenResponseString = "tokenResponseString";

    private IOException exception;

    @BeforeEach
    void setup(){

        client = mock(CloseableHttpClient.class);
        response = mock(CloseableHttpResponse.class);
        httpHelper = mock(HttpHelper.class);
        parser = mock(Parser.class);
        urlApi = "https://gentle-eyrie-95237.herokuapp.com";
        userName = "kinexo";
        password = "kinexo";

        apiUser = new ApiUser(urlApi, userName, password, client, httpHelper, parser);

        tokenResponse = mock(TokenResponse.class);
        user = mock(User.class);
        userList = new ArrayList<>();
        exception = new IOException();
    }

    @Test
    void shouldReturnUserWhenNotHappensAnExceptionById() throws IOException {
        setupInitial(false);

        when(parser.parseToObject(eq(httpResponseString), eq(User.class))).thenReturn(user);

        apiUser.getUserById(1);

        verify(parser, times(1)).parseToObject(httpResponseString, User.class);
    }

    @Test
    void shouldReturnNullWhenHappensAnExceptionById() throws IOException {
        setupInitial(true);

        when(parser.parseToObject(eq(httpResponseString), eq(User.class))).thenReturn(null);

        apiUser.getUserById(1);

        verify(parser, times(0)).parseToObject(httpResponseString, User.class);
    }

    @Test
    void shouldReturnListUsersWhenNotHappensAnExceptionAllUsers() throws IOException {
        setupInitial(false);

        userList = new ArrayList<>();
        userList.add(user);
        when(parser.parseToListObject(eq(httpResponseString), eq(User.class))).thenReturn(userList);

        List<User> users = apiUser.getAllUsers();

        verify(parser, times(1)).parseToListObject(httpResponseString, User.class);
    }

    @Test
    void shouldReturnNullWhenHappensAnExceptionAllUsers() throws IOException {
        setupInitial(true);

        userList = new ArrayList<>();
        when(parser.parseToListObject(eq(httpResponseString), eq(User.class))).thenReturn(userList);

        List<User> users = apiUser.getAllUsers();

        verify(parser, times(0)).parseToListObject(httpResponseString, User.class);
    }

    public void setupInitial(Boolean excep) throws IOException {
        when(client.execute(any(HttpPost.class))).thenReturn(response);
        when(httpHelper.getBody(any(CloseableHttpResponse.class))).thenReturn(httpResponseString);
        when(parser.parseToObject(eq(httpResponseString), eq(TokenResponse.class))).thenReturn(tokenResponse);
        when(tokenResponse.getToken()).thenReturn(tokenResponseString);

        if (excep){
            when(client.execute(any(HttpGet.class))).thenThrow(new IOException("Error occurred"));
        } else {
            when(client.execute(any(HttpGet.class))).thenReturn(response);
        }
        when(httpHelper.getBody(any(CloseableHttpResponse.class))).thenReturn(httpResponseString);
    }

}

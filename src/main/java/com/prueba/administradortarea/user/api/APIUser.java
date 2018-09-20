package com.prueba.administradortarea.user.api;

public interface APIUser {

    String login(String username, String password);

    String getUsers();

    String getUsersById(Integer userId);
}

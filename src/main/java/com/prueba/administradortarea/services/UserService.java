package com.prueba.administradortarea.services;

import com.prueba.administradortarea.models.domain.external.User;

import java.util.List;


public interface UserService {

    List<User> getUsers();

    Boolean existUser(Integer idUser);
}

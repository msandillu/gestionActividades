package com.prueba.administradortarea.services;

import com.prueba.administradortarea.models.domain.external.User;
import com.prueba.administradortarea.user.api.external.ApiUser;

import java.util.*;


public class UserServiceImpl implements UserService {

    private ApiUser apiUser;

    public UserServiceImpl(ApiUser apiUser) {
        this.apiUser = apiUser;
    }

    @Override
    public List<User> getUsers() {
        return apiUser.getAllUsers();
    }

    private User findUser(Integer idUser) {
        return apiUser.getUserById(idUser);
    }

    @Override
    public Boolean existUser(Integer idUser){
        return findUser(idUser) != null;
    }

}

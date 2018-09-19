package com.prueba.administradortarea.services;

import com.prueba.administradortarea.models.domain.User;

import java.util.*;


public class UserServiceImpl implements UserService {

    //private TaskRepository taskRepository;

    //TODO: Inyectar al API REST de user
    /*public ExternalUserServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }*/


    @Override
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        return userList;
    }

    private User findUser(Integer idUser) {
        //TODO: Usar API REST de User
        User user = null;
        if (idUser == 1) {
            user = new User();
            user.setId(1);
            user.setFirstName("Maxi");
            user.setLastName("Sandillu");
            user.setBirthDate(new Date());
            user.setCityName("Rafaela");
        }
        return user;
    }

    @Override
    public Boolean existUser(Integer idUser){
        return findUser(idUser) != null ? Boolean.TRUE : Boolean.FALSE;
    }

}

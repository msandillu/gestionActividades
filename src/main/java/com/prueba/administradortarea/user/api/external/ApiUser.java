package com.prueba.administradortarea.user.api.external;

import com.prueba.administradortarea.models.domain.external.User;

import java.util.List;

public interface ApiUser {

    List<User> getAllUsers();

    User getUserById(Integer userId);

}

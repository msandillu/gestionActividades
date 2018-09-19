package com.prueba.administradortarea.user.api;

import com.prueba.administradortarea.exception.ApiException;
import com.prueba.administradortarea.parsers.Parser;
import com.prueba.administradortarea.services.UserService;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.io.IOException;


public class AuthenticationHandlerImpl implements AuthenticationHandler{

    UserService userService;
    Parser parser;

    public AuthenticationHandlerImpl(UserService userService, Parser parser) {
        this.userService = userService;
        this.parser = parser;
    }


    public void validate(Request request, Response response) throws IOException {

        //Reviso si el Header viene el X-Caller-Id
        String userId = request.headers("X-Caller-Id");
        if (userId == null){
            response.status(HttpStatus.UNAUTHORIZED_401);
            ApiException apiException = new ApiException();
            apiException.setDescription("Not found header X-Caller-Id");
            Spark.halt(401, parser.parseToString(apiException));
        }

        //Reviso si obtengo un entero y si existe el usuario
        Integer userIdInt;
        try{
            userIdInt = Integer.parseInt(userId);
        } catch (NumberFormatException excepcion){
            userIdInt = null;
        }

        Boolean existUser = false;
        if (userIdInt != null){
            existUser = userService.existUser(userIdInt);
        }

        if (userId == null || !existUser){
            response.status(HttpStatus.UNAUTHORIZED_401);
            ApiException apiException = new ApiException();
            apiException.setDescription("Not found the User");
            Spark.halt(401, parser.parseToString(apiException));
        }
    }

}

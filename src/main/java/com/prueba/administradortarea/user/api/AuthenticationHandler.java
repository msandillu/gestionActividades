package com.prueba.administradortarea.user.api;

import spark.Request;
import spark.Response;

import java.io.IOException;

public interface AuthenticationHandler {

    Integer validate(Request request, Response response) throws IOException;
}

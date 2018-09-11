package controllers;

import Enumerate.StatusResponse;
import com.google.gson.Gson;
import daos.StandardResponse;
import daos.Tarea;
import services.TareaService;
import services.TareaServiceImpl;

import java.util.Collection;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

public class TareaController {

    public void setup (){

        final TareaService tareaService = new TareaServiceImpl();

        post("/tareas", (request, response) -> {
            response.type("application/json");

            Tarea tarea = new Gson().fromJson(request.body(), Tarea.class);
            tareaService.addTarea(tarea);

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        get("/tareas", (request, response) -> {
            response.type("application/json");

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(tareaService.getTarea())));
        });

        get("/tareas/:id", (request, response) -> {
            response.type("application/json");

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(tareaService.getTarea(Integer.parseInt(request.params(":id"))))));
        });

        put("/tareas/:id", (request, response) -> {
            response.type("application/json");

            Tarea toEdit = new Gson().fromJson(request.body(), Tarea.class);
            Tarea tareaEditada = tareaService.editTarea(toEdit);

            if (tareaEditada != null) {
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(tareaEditada)));
            } else {
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, new Gson().toJson("Tarea no encontrada para editar")));
            }
        });

        delete("/tareas/:id", (request, response) -> {
            response.type("application/json");

            tareaService.deleteTarea(Integer.parseInt(request.params(":id")));
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "tarea eliminada"));
        });

    }

}

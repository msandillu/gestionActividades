package com.prueba.administradortarea;

import com.prueba.administradortarea.initialbd.InitialDataBase;
import com.prueba.administradortarea.router.Router;
import spark.Spark;

import java.io.IOException;
import java.sql.SQLException;


public class main {

    private static int port = 8081;

    public main(){
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, SQLException {
        new InitialDataBase().crearDB(false);
        Spark.port(port);
        new Router().init();
    }
}

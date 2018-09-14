package com.prueba.administradortarea;

import com.prueba.administradortarea.router.Router;
import spark.Spark;


public class main {

    private static int port = 8081;

    public main(){
    }

    public static void main(String[] args) {
        Spark.port(port);
        new Router().init();
    }
}

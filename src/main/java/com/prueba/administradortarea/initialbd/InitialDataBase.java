package com.prueba.administradortarea.initialbd;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InitialDataBase {

    private static String leerSqlFile(String urlFile) throws java.io.IOException {
        String filePath = String.format("%s/%s", System.getProperty("user.dir"), urlFile);
        byte[] buffer = null;
        //System.out.println(filePath);
        File file = new File(filePath);

        buffer = new byte[(int) file.length()];
        BufferedInputStream f = null;
        try {
            f = new BufferedInputStream(new FileInputStream(filePath));
            f.read(buffer);
        } finally {
            if (f != null) {
                f.close();
            }
        }
        return new String(buffer);
    }


    public void crearDB(Boolean useH2) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, SQLException {
        //ejecutamos los scripts de eliminacion y creacion de la BD
        String deleteTables = null;
        String createTables = null;
        Connection con = null;
        if (useH2){
            deleteTables = this.leerSqlFile("src/main/java/com/prueba/administradortarea/query/deletebdh2.sql");
            createTables = this.leerSqlFile("src/main/java/com/prueba/administradortarea/query/createbdh2.sql");
            Class.forName("org.h2.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:h2:/home/msandillu/Documents/administrador-tareas/h2/test", "sa", "sa");
        } else{
            deleteTables = this.leerSqlFile("src/main/java/com/prueba/administradortarea/query/deletebd.sql");
            createTables = this.leerSqlFile("src/main/java/com/prueba/administradortarea/query/createbd.sql");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "");
        }
        java.sql.Statement stm = con.createStatement();
        stm.execute(deleteTables);
        stm.execute(createTables);
        stm.close();
        con.close();
    }
}

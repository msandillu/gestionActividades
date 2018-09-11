import controllers.TareaController;
import spark.Spark;


public class main {


    public static void main(String[] args) {
        Spark.port(8081);

        TareaController tareaController = new TareaController();

        tareaController.setup();
    }
}

import controllers.TaskController;
import spark.Spark;


public class main {


    public static void main(String[] args) {
        Spark.port(8081);

        TaskController taskController = new TaskController();

        taskController.setup();
    }
}

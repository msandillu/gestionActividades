package com.prueba.administradortarea.router;


import com.prueba.administradortarea.controllers.TaskController;
import com.prueba.administradortarea.user.api.AuthenticationHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spark.Spark;
import spark.servlet.SparkApplication;

public class Router implements SparkApplication {

    private static final String springConfigPath = "spring/application-config.xml";
    private final TaskController taskController;
    private final ApplicationContext context;
    private final AuthenticationHandler authenticationHandler;

    public Router() {
        context = new ClassPathXmlApplicationContext(springConfigPath);
        taskController = context.getBean("taskController", TaskController.class);
        authenticationHandler = context.getBean("authenticationHandler", AuthenticationHandler.class);
    }

    @Override
    public void init() {

        String scope = getScope();

        Spark.before((request, response) -> {
            Integer idUser = authenticationHandler.validate(request, response);
            taskController.setUserAuthenticateId(idUser);
        });

        Spark.path("/api", () -> {
            Spark.post("/tasks", taskController.postTasks);
        });

        Spark.path("/api", () -> {
            Spark.get("/tasks", taskController.getTasks);
        });

        Spark.path("/api", () -> {
            Spark.get("/tasks/:id", taskController.getTasksById);
        });

        Spark.path("/api", () -> {
            Spark.delete("/tasks/:id", taskController.deleteTasksById);
        });
    }

    @Override
    public void destroy() {
        if ((this.context instanceof ConfigurableApplicationContext)) {
            ((ConfigurableApplicationContext) this.context).close();
        }
    }

    private String getScope() {
        return context.getEnvironment().getProperty("scope");
    }

}

package com.prueba.administradortarea.router;


import com.prueba.administradortarea.controllers.TaskController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spark.Spark;
import spark.servlet.SparkApplication;

public class Router implements SparkApplication {

    private static final String springConfigPath = "spring/application-config.xml";
    private final TaskController taskController;
    private final ApplicationContext context;

    public Router() {
        context = new ClassPathXmlApplicationContext(springConfigPath);
        taskController = context.getBean("taskController", TaskController.class);
    }

    @Override
    public void init() {

        String scope = getScope();

        Spark.path("/api", () -> {
            Spark.post("/tasks", taskController.postTasks);
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

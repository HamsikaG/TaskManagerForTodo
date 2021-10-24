package todolist;

import todolist.db.daos.MongoDBFactoryConnection;
import todolist.db.daos.MongoDBManaged;
import todolist.db.daos.TodoDAO;
import todolist.health.DropwizardMongoDBHealthCheck;
import todolist.resources.ToDoResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import javax.servlet.DispatcherType;
import java.util.EnumSet;
import javax.servlet.FilterRegistration;


public class TodoApplication extends Application<TodoConfiguration> {
    public static void main(String[] args) throws Exception {
        new TodoApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }


    @Override
    public void initialize(final Bootstrap<TodoConfiguration> bootstrap) {
    }


    @Override
    public void run(TodoConfiguration configuration,
                    Environment environment) {

        final MongoDBFactoryConnection mongoDBManagerConn = new MongoDBFactoryConnection(configuration.getMongoDBConnection());

        final MongoDBManaged mongoDBManaged = new MongoDBManaged(mongoDBManagerConn.getClient());

        final TodoDAO todoDAO = new TodoDAO(mongoDBManagerConn.getClient()
                .getDatabase(configuration.getMongoDBConnection().getDatabase())
                .getCollection("todos"));

        environment.lifecycle().manage(mongoDBManaged);
      //  environment.jersey().register(toDoResource);
        environment.jersey().register(new ToDoResource(todoDAO));
        environment.healthChecks().register("DropwizardMongoDBHealthCheck",
                new DropwizardMongoDBHealthCheck(mongoDBManagerConn.getClient()));


        final FilterRegistration.Dynamic corsFilter =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        corsFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        corsFilter.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM,
                "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        corsFilter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        corsFilter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        corsFilter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        corsFilter.setInitParameter("allowedHeaders",
                "Cache-Control,If-Modified-Since,Pragma,Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");

        corsFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

    }

}
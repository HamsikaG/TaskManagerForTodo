package todolist.resources;

import com.codahale.metrics.annotation.Timed;
import org.bson.types.ObjectId;
import todolist.api.Todo;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import todolist.db.daos.TodoDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/taskManager")
@Produces(MediaType.APPLICATION_JSON)
public class ToDoResource {
    private TodoDAO toDoDAO;
    private static final Logger LOGGER = LoggerFactory.getLogger(ToDoResource.class);

    public ToDoResource(final TodoDAO toDoDAO) {
        this.toDoDAO = toDoDAO;
    }

    @GET
    @Path("/todos")
    @Timed
    public List<Todo> myTodos() {
        LOGGER.info("Fetching all data in collection");
        return toDoDAO.getAll();
    }

    @POST
    @Path("/save")
    @Timed
    public Response saveToDo(Todo todo) {
        LOGGER.info("Persist todo in collection with the information: {}", todo);
        ObjectId id = toDoDAO.save(todo);
        todo.setId(id.toString());
        return Response.status(Response.Status.CREATED).entity(todo).build();
    }

    @POST
    @Path("/update")
    @Produces(MediaType.TEXT_PLAIN)
    @Timed
    public Response updateToDo(Todo todo) {
        LOGGER.info("Update todo in collection with the information: {}", todo);
        toDoDAO.update(new ObjectId(todo.getId()) ,todo);
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/remove/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Timed
    public Response deleteToDo(Todo todo) {
        LOGGER.info("Deleting todo in collection: {}", todo);
        toDoDAO.delete(new ObjectId(todo.getId()));
        return Response.status(Response.Status.OK).entity(todo.getId()).build();
    }
}

package todolist.resources;

import com.codahale.metrics.annotation.Timed;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import todolist.api.Todo;
import todolist.db.daos.TodoDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public Response myTodos() {
        LOGGER.info("Fetching all data in collection");
        return Response.status(Response.Status.CREATED).entity(toDoDAO.getAll()).build();
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

    @DELETE
    @Path("/{id}")
    @Timed
    public Response deleteToDo(@PathParam("id") String id) {
        LOGGER.info("Deleting todo in collection: {}", id);
        toDoDAO.delete(new ObjectId(id));
        return Response.status(Response.Status.OK).entity(id).build();
    }
}

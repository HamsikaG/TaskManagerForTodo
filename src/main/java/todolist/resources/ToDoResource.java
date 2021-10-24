package todolist.resources;

import com.codahale.metrics.annotation.Timed;
import todolist.api.Todo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import todolist.db.daos.TodoDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/todo")
@Produces(MediaType.APPLICATION_JSON)
public class ToDoResource {
//    private final String todoDescription;
//    private final String todoFinishDate;
//    private final Boolean isDone;
   private final AtomicLong id = new AtomicLong();


    private TodoDAO toDoDAO;
    private static final Logger LOGGER = LoggerFactory.getLogger(ToDoResource.class);

    /**
     * Constructor.
     *
     * @param toDoDAO the dao donut.
     */



//    public ToDoResource(String todoDescription, String todoFinishDate, Boolean isDone) {
//        this.todoDescription = todoDescription;
//        this.todoFinishDate = todoFinishDate;
//        this.isDone = isDone;
//        this.id = new AtomicLong();
//    }
    public ToDoResource(final TodoDAO toDoDAO) {
        this.toDoDAO = toDoDAO;
    }

    @GET
    @Timed
    public List<Todo> myTodos() {
        return null;
    }

    @POST
    @Path("/save")
    @Timed
    public Response saveToDo(Todo todo) {
        todo.setId(id.incrementAndGet());
        LOGGER.info("Persist todo in collection with the information: {}", todo);
        toDoDAO.save(todo);
        return Response.status(Response.Status.CREATED).entity(todo.getId()).build();
        // return new Todo(id.incrementAndGet(), todo.getTodoDescription() ,  todo.getTodoFinishDate(), todo.getIsDone());
    }

    @POST
    @Path("/update")
    @Produces(MediaType.TEXT_PLAIN)
    @Timed
    public String updateToDo(Todo todo) {
        return "ToDo updated. Total count: ";
    }

    @POST
    @Path("/remove")
    @Produces(MediaType.TEXT_PLAIN)
    @Timed
    public String deleteToDo(Todo todo) {
        return "ToDo removed. Total count: ";
    }
}

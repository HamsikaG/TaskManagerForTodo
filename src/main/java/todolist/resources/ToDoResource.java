package todolist.resources;

import com.codahale.metrics.annotation.Timed;
import todolist.api.Todo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Path("/todo")
@Produces(MediaType.APPLICATION_JSON)
public class ToDoResource {
    private final String todoDescription;
    private final String todoFinishDate;
    private final Boolean isDone;
    private final AtomicLong id;


    public ToDoResource(String todoDescription, String todoFinishDate, Boolean isDone) {
        this.todoDescription = todoDescription;
        this.todoFinishDate = todoFinishDate;
        this.isDone = isDone;
        this.id = new AtomicLong();
    }

    @GET
    @Timed
    public List<Todo> myTodos() {
        return null;
    }

    @POST
    @Path("/save")
    @Timed
    public Todo saveToDo(Todo todo) {
        return new Todo(id.incrementAndGet(), todo.getTodoDescription() ,  todo.getTodoFinishDate(), todo.getIsDone());
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

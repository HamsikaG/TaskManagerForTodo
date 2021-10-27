package todolist.util;


import todolist.api.Todo;
import org.bson.Document;

public class TodoMapper {

    /**
     * Map objects {@link Document} to {@link Todo}.
     *
     * @param todoDocument the information document.
     * @return A object {@link Todo}.
     */
    public static Todo map(final Document todoDocument) {
        final Todo todo = new Todo();
        todo.setId(String.valueOf(todoDocument.getObjectId("_id")));
        todo.setTodoDescription(todoDocument.getString("description"));
        todo.setTodoFinishDate(todoDocument.getString("finishDate"));
        todo.setIsDone(todoDocument.getBoolean("isDone"));
        return todo;
    }
}
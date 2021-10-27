package todolist.db.daos;

import java.util.ArrayList;
import java.util.List;

import todolist.api.Todo;
import todolist.util.TodoMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;


public class TodoDAO {

    /**
     * The collection of Todos
     */
    final MongoCollection<Document> todoCollection;

    /**
     * Constructor.
     *
     * @param todoCollection the collection of todos.
     */
    public TodoDAO(final MongoCollection<Document> todoCollection) {
        this.todoCollection = todoCollection;
    }

    /**
     * Find all todos.
     *
     * @return the todos.
     */
    public List<Todo> getAll() {
        final MongoCursor<Document> todos = todoCollection.find().iterator();
        final List<Todo> todosFind = new ArrayList<>();
        try {
            while (todos.hasNext()) {
                final Document todo = todos.next();
                todosFind.add(TodoMapper.map(todo));
            }
        } finally {
            todos.close();
        }
        return todosFind;
    }

    public ObjectId save(final Todo todo){
        final Document saveTodo =new Document("description", todo.getTodoDescription())
                .append("finishDate", todo.getTodoFinishDate()).append("isDone", todo.getIsDone());
        todoCollection.insertOne(saveTodo);
        ObjectId id = (ObjectId)saveTodo.get( "_id" );
        return id;
    }


    /**
     * Update a register.
     *
     * @param id the identifier.
     * @param todo the object to update.
     */
    public void update(final ObjectId id, final Todo todo) {
        todoCollection.updateOne(new Document("_id", id),
                new Document("$set", new Document("isDone", todo.getIsDone()))
        );
    }

    /**
     * Delete a register.
     * @param id    the identifier.
     */
    public void delete(final ObjectId id){
        todoCollection.deleteOne(new Document("_id", id));
    }
}
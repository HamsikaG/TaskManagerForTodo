package todolist;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;
import todolist.db.configuration.MongoDBConnection;

public class TodoConfiguration extends Configuration {
    @NotEmpty
    private String todoDescription;

    @NotEmpty
    private String todoFinishDate;

    private Boolean isDone;
    private MongoDBConnection mongoDBConnection;

    @JsonProperty
    public String getTodoDescription() { return todoDescription; }

    @JsonProperty
    public void setTodoDescription(String todoDescription) {
        this.todoDescription = todoDescription;
    }

    @JsonProperty
    public String getTodoFinishDate() {
        return todoFinishDate;
    }

    @JsonProperty
    public void setTodoFinishDate(String date) {
        this.todoFinishDate = date;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Gets the mongoDBConnection.
     *
     * @return the value mongoDBConnection.
     */
    public MongoDBConnection getMongoDBConnection() {
        return mongoDBConnection;
    }

    /**
     * Sets the mongoDBConnection.
     *
     * @param mongoDBConnection value.
     */
    public void setMongoDBConnection(final MongoDBConnection mongoDBConnection) {
        this.mongoDBConnection = mongoDBConnection;
    }

}
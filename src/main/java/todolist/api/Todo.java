package todolist.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Todo {
    private long id;

    private String todoDescription;
    private String todoFinishDate;
    private Boolean isDone;

    public Todo() {
        // Jackson deserialization
    }

    public Todo(long id, String todoDescription, String todoFinishDate, Boolean isDone) {
        this.id = id;
        this.todoDescription = todoDescription;
        this.todoFinishDate = todoFinishDate;
        this.isDone = isDone;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getTodoDescription() {
        return todoDescription;
    }

    @JsonProperty
    public String getTodoFinishDate() {  return todoFinishDate;   }

    @JsonProperty
    public Boolean getIsDone() { return isDone;   }


    @Override
    public String toString() {
        return "Todo{" + "id=" + id + ", todoDescription='" + todoDescription  +  '\'' + ", todoFinishDate='" + todoFinishDate + '\''+ ", isDone" + isDone.toString() +'}';
    }
}
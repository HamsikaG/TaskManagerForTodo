package todolist.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Todo implements Serializable {
    private long id;

    public void setId(long id) {
        this.id = id;
    }

    public void setTodoDescription(String todoDescription) {
        this.todoDescription = todoDescription;
    }

    public void setTodoFinishDate(String todoFinishDate) {
        this.todoFinishDate = todoFinishDate;
    }

    public void setIsDone(Boolean done) {
        isDone = done;
    }

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
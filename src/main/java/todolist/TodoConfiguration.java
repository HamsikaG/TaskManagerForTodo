package todolist;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;

public class TodoConfiguration extends Configuration {
    @NotEmpty
    private String todoDescription;

    @NotEmpty
    private String todoFinishDate;

    private Boolean isDone;

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


}
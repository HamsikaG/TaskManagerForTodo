Todo list with Dropwizard
=========================

A simple todo list sample application implemented using dropwizad
as the core framework with a simple UI that consumes the REST API.



Specification - DRAFT 1 -
------------------------

The service will be mounted on $BASE_URL. It will expose a simple *taskManager* resource that has the following API:

* GET     /taskManager/todos - will fetch and return the list of todos from db
* POST    /taskManager/save - will create another todo, persist in db and return todo with id

* POST     /taskManager/update/{id}  - will update the todo specified by {id}
* DELETE  /taskManager/{id}  - will delete the todo specified by {id}

A **todo** has the following information:
  - id : assigned by server on creation 
  - todoDescription: description of todo task
  - todoFinishDate: end date for todo task
  - isDone: status of task


How to run
----------
  - clone: **https://github.com/HamsikaG/TaskManagerForTodo && cd TaskManagerForTodo**
  - build the project with **$ mvn clean install**  
  - run it: **java -jar target/dropwizardArtifactId-1.0-SNAPSHOT.jar server todo-list.yml**
  - goto the view: http://localhost:8080




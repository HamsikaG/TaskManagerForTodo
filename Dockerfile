#FROM java:8-jdk-alpine
#WORKDIR /usr/app
#COPY ./target/dropwizardArtifactId-1.0-SNAPSHOT.jar dropwizardArtifactId-1.0-SNAPSHOT.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "/dropwizardArtifactId-1.0-SNAPSHOT.jar"]

FROM openjdk:8u121-jre-alpine
WORKDIR /var/dropwizard-rest-stub

ADD target/dropwizardArtifactId-1.0-SNAPSHOT.jar /var/dropwizard-rest-stub/dropwizard-rest-stub.jar
ADD todo-list.yml /var/dropwizard-rest-stub/todo-list.yml

EXPOSE 9000 9001

ENTRYPOINT ["java", "-jar", "dropwizard-rest-stub.jar", "server", "todo-list.yml"]
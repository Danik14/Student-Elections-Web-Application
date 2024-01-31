FROM maven:3-openjdk-17 as poc_builder

ENV HOME=/usr/app

RUN mkdir -p $HOME

WORKDIR $HOME

ADD pom.xml $HOME
ADD src $HOME/src/
RUN --mount=type=cache,target=/root/.m2 mvn clean package

FROM openjdk:17-jdk-slim-buster as builder

ENV HOME=/opt/app
WORKDIR $HOME

COPY --from=poc_builder /usr/app/target/Student-Elections.jar $HOME

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Student-Elections.jar"]
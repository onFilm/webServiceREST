FROM openjdk:11
WORKDIR /src
MAINTAINER mail2prajwal12
COPY ./target/webServiceREST-1.0-SNAPSHOT.jar /src/app.jar
ENTRYPOINT ["java","-jar","-Dtags=@test","-Denv=int","/src/app.jar"]
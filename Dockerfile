FROM mail2prajwal12/alpine-oraclejdk18-maven
WORKDIR /src
MAINTAINER mail2prajwal12
COPY . /src
RUN mvn -B package --file /src/pom.xml
COPY ./target/webServiceREST-1.0-SNAPSHOT.jar /app.jar
RUN apk add tree && tree -d /
ENTRYPOINT ["java","-jar","-Dtags=@test","-Denv=int","/src/app.jar"]
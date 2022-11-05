FROM mail2prajwal12/alpine-oraclejdk18-maven
WORKDIR /src
MAINTAINER mail2prajwal12
COPY . /src
RUN mvn -B package --file /src/pom.xml
RUN cp /src/target/webServiceREST-1.0-SNAPSHOT.jar /src/app.jar
RUN apk add tree && tree /src
ENTRYPOINT ["java","-jar","-Dtags=@test","-Denv=int","/src/app.jar"]
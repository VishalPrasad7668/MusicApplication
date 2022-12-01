FROM openjdk
WORKDIR usr/lib
ENV MONGO_DATABASE="trackkdb"
ENV MONGO_URI="MONGODB://mongo-service2:27017/trackkdb"

ADD ./target/UserTrackService-0.0.1-SNAPSHOT.jar /usr/lib/UserTrackService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","UserTrackService-0.0.1-SNAPSHOT.jar"]
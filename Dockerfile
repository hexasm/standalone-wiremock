FROM openjdk:17-jdk-oracle
COPY target/standalone-mock.jar /opt/tanbit/standalone-mock.jar
COPY src/main/resources/ /opt/tanbit/src/main/resources/
RUN ls -la /opt/tanbit/src/main/resources/*
WORKDIR /opt/tanbit
CMD java -jar standalone-mock.jar
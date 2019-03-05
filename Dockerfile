FROM maven:3.6.0-jdk-12-alpine as builder
# FROM openjdk:11-jre-slim-stretch

ENV DB_NAME=CollectionDev
ENV DB_SERVER=sql_server.TakeOnContainerNet
ENV DATASOURCE_USERNAME=sa
ENV DATASOURCE_PASSWORD=aVerySecurePassword!123
ENV eureka.instance.preferIpAddress=true

WORKDIR /validation_pl

COPY . /validation_pl
RUN mvn -Dmaven.test.skip=true clean package
CMD java -Dserver.port=0 -jar target/validation-persistence-layer-1.0-SNAPSHOT.jar

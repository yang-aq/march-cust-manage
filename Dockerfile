FROM openjdk:8
MAINTAINER Liang.Cheng@pccw.com
LABEL app="march-demo" version="0.0.1"
COPY march-demo-web/target/march-demo-web-0.0.1-SNAPSHOT.jar app.jar
CMD java -jar app.jar

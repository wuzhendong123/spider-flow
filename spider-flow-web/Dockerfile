FROM java:8-alpine

MAINTAINER wuzhendong
VOLUME /opt/man/

#RUN mkdir -p /spider-flow

#WORKDIR /spider-flow

EXPOSE 8077

ADD target/spider-flow.jar /app

ENTRYPOINT ["java","-jar","/app.jar"]
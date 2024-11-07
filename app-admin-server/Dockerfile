FROM amazoncorretto:21

WORKDIR /app/

RUN yum install -y wget git curl

COPY target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

FROM amazoncorretto:21

WORKDIR /app/

RUN yum install -y wget git curl

# install otel agent
RUN wget https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar

COPY target/*.jar /app/app.jar

ENTRYPOINT ["java", "-javaagent:/app/opentelemetry-javaagent.jar", "-Dotel.service.name=app-spring", "-jar", "app.jar"]

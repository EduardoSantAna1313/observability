# FROM maven:3.9.6 AS builder
#
# WORKDIR /app/
#
# COPY src /app/
# COPY pom.xml /app/
#
# CMD ["mvn", "clean", "install", "-DskipTests"]

FROM amazoncorretto:21

WORKDIR /app/

# COPY --from=builder /app/target/*.jar app.jar

COPY target/*.jar /app/app.jar

RUN yum install -y wget git curl

# install otel agent
RUN wget https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar

ENTRYPOINT ["java", "-javaagent:/app/opentelemetry-javaagent.jar", "-Dotel.service.name=observabilitu", "-jar", "app.jar"]
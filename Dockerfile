# Previewsly run: `gradle bootJar`
FROM openjdk:8-jre-slim
EXPOSE 8080 5005
VOLUME /tmp
COPY build/libs/mongo-kubernetes-demo-0.0.1-SNAPSHOT.jar app.jar
ENV _JAVA_OPTIONS '-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
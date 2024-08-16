FROM openjdk:11-jre-slim
ADD target/ms-admin.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8081
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY --from=build /Softwares/SpringProject/Enrollment/target/Enrollment.jar /app/Enrollment.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080
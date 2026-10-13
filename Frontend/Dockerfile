# Build the WAR
FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn -B -q package -DskipTests

# Run it on Tomcat
FROM tomcat:9.0-jdk11
COPY --from=build /build/target/*.war /usr/local/tomcat/webapps/Frontend.war
EXPOSE 8080

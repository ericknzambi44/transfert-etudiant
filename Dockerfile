# Étape 1 : Build du jar avec Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Étape 2 : Exécution du conteneur
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
# On copie le fichier .jar généré (vérifie si le projet génère bien dans /target)
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
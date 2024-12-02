# Etapa 1: Construcción
FROM maven:3.9.3-eclipse-temurin-17 AS builder
WORKDIR /app

# Copiar archivos del proyecto y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente
COPY src ./src

# Compilar el proyecto
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiar el JAR generado desde la etapa de construcción
COPY --from=builder /app/target/*.jar app.jar

# Configurar el punto de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]

# Exponer el puerto en el que la app se ejecuta
EXPOSE 8080

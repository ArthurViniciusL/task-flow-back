# ==================================
# ESTÁGIO 1: BUILD
# ==================================
FROM eclipse-temurin:21-jdk-alpine AS build

# Instala Maven no contêiner de build
RUN apk add --no-cache maven

# Define o diretório de trabalho
WORKDIR /app

# Copia o pom.xml primeiro para aproveitar cache de dependências do Docker
COPY pom.xml .

# Baixa todas as dependências (esta camada será cacheada)
RUN mvn dependency:go-offline -B

# Copia o código fonte da aplicação
COPY src ./src

# Empacota a aplicação Spring Boot em um JAR executável
RUN mvn clean package -DskipTests -B

# ==================================
# ESTÁGIO 2: RUNTIME
# ==================================
FROM eclipse-temurin:21-jre-alpine

# Define o diretório de trabalho
WORKDIR /app

# Copia apenas o JAR compilado do estágio anterior
COPY --from=build /app/target/task-flow-app-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta em que a aplicação Spring Boot será executada
EXPOSE 8080

# Define o comando para executar a aplicação JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
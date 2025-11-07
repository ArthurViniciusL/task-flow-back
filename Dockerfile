# Use Eclipse Temurin ao invés de OpenJDK (que foi descontinuado)
FROM eclipse-temurin:21-jdk-alpine

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo pom.xml para que as dependências possam ser baixadas em cache
COPY pom.xml .

# Copia o código fonte da aplicação
COPY src ./src

# Copia o script mvnw e o diretório .mvn para permitir a construção com o Maven Wrapper
COPY mvnw .
COPY .mvn .mvn

# Concede permissão de execução ao script mvnw
RUN chmod +x mvnw

# Empacota a aplicação Spring Boot em um JAR executável
# Usa o Maven Wrapper para garantir a versão correta do Maven
RUN ./mvnw clean package -DskipTests

# Expõe a porta em que a aplicação Spring Boot será executada
EXPOSE 8080

# Define o comando para executar a aplicação JAR
ENTRYPOINT ["java", "-jar", "target/task-flow-app-0.0.1-SNAPSHOT.jar"]
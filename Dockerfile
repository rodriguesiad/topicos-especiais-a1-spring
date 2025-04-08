FROM gradle:jdk21 AS build

# Diretório de trabalho da aplicação
ENV PROJECT_HOME /usr/src/app
WORKDIR $PROJECT_HOME

# Copia os arquivos gradle necessários e o wrapper
COPY gradle/wrapper/gradle-wrapper.properties gradle/wrapper/gradle-wrapper.jar gradle/wrapper/ ./gradle/wrapper/
COPY gradlew ./
COPY build.gradle settings.gradle ./

# Garante que o gradlew tenha permissões de execução
RUN chmod +x gradlew

# Força o uso do protocolo TLS 1.2
RUN echo "org.gradle.jvmargs=-Dhttps.protocols=TLSv1.2" >> gradle.properties

# Baixa as dependências do Gradle sem compilar os arquivos
RUN ./gradlew --no-daemon dependencies --stacktrace

# Copia o código fonte da aplicação
COPY ./src ./src

# Constroe o JAR da aplicação
RUN ./gradlew build -x test --stacktrace

# Imagem OpenJDK 21 para rodar o jar
FROM openjdk:21-jdk-slim

# Diretório de trabalho na imagem final
ENV PROJECT_HOME /usr/src/app
WORKDIR $PROJECT_HOME

# Copia o arquivo JAR da imagem de construção
COPY --from=build $PROJECT_HOME/build/libs/*.jar app.jar

# Expoem a porta da aplicação
EXPOSE 8080

# Define o comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]
# Usando a imagem base do OpenJDK (versão 17 como exemplo)
FROM openjdk:23-jdk-slim

# Define um argumento para especificar o JAR gerado
ARG JAR_FILE=target/*.jar

# Cria o diretório para o aplicativo dentro do container
RUN mkdir /app

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Passando variáveis de ambiente para configurar o banco de dados
ENV SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/db_Genetic_Sequencing
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=05042000

# Copia o arquivo JAR do diretório alvo para o container
COPY ${JAR_FILE} app.jar

# Comando que será executado quando o container iniciar
CMD ["java", "-jar", "app.jar"]

# Define a porta que será exposta pelo container
EXPOSE 8080

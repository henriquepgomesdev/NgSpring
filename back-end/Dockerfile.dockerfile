# Etapa 1: Constru��o com Maven
FROM maven:3.8.4-openjdk-17 AS build

# Defina o diret�rio de trabalho onde o c�digo ser� copiado
WORKDIR /app

# Copie os arquivos do projeto para o diret�rio de trabalho no container
COPY . /app

# Execute o comando para gerar o arquivo .jar
RUN mvn clean package

# Etapa 2: Imagem final para rodar a aplica��o
FROM openjdk:17-jdk-slim

# Defina o diret�rio de trabalho onde o .jar ser� copiado
WORKDIR /app

# Copie o arquivo .jar gerado pela etapa anterior
COPY --from=build /app/target/your-app-name.jar app.jar

# Exponha a porta onde o app estar� rodando
EXPOSE 8090

# Comando para rodar a aplica��o
ENTRYPOINT ["java", "-jar", "app.jar"]

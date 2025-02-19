# Etapa 1: Construção com Maven
FROM maven:3.8.4-openjdk-17 AS build

# Defina o diretório de trabalho onde o código será copiado
WORKDIR /app

# Copie os arquivos do projeto para o diretório de trabalho no container
COPY . /app

# Execute o comando para gerar o arquivo .jar
RUN mvn clean package

# Etapa 2: Imagem final para rodar a aplicação
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho onde o .jar será copiado
WORKDIR /app

# Copie o arquivo .jar gerado pela etapa anterior
COPY --from=build /app/target/your-app-name.jar app.jar

# Exponha a porta onde o app estará rodando
EXPOSE 8090

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

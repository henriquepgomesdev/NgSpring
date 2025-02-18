# Use uma imagem do OpenJDK
FROM openjdk:17-jdk-alpine

# Instale o Maven
RUN apk update && apk add maven

# Defina o diret�rio de trabalho
WORKDIR /app

# Copie os arquivos do projeto para o container
COPY . .

# Instale as depend�ncias do projeto (executando mvn clean install)
RUN mvn clean install

# Exponha a porta em que o app vai rodar
EXPOSE 8080

# Comando para rodar o projeto (ajuste conforme necess�rio)
CMD ["java", "-jar", "target/your-app-name.jar"]

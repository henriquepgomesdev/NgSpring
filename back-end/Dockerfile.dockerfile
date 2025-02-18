# Use uma imagem do Java
FROM openjdk:17-jdk-alpine

# Defina o diret�rio de trabalho
WORKDIR /app

# Copie os arquivos do projeto para o container
COPY . .

# Instale as depend�ncias do projeto (se usar Maven)
RUN mvn clean install

# Exponha a porta em que o app vai rodar
EXPOSE 8080

# Comando para rodar o projeto (ajuste conforme necess�rio)
CMD ["java", "-jar", "target/your-app-name.jar"]

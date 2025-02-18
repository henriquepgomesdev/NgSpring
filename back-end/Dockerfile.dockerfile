# Use uma imagem do OpenJDK
FROM openjdk:17-jdk-alpine

# Instale o Maven
RUN apk update && apk add maven

# Defina o diretório de trabalho
WORKDIR /app

# Copie todos os arquivos do projeto (inclusive pom.xml)
COPY . .

# Liste os arquivos no diretório para verificar a presença do pom.xml
RUN ls -la /app

# Instale as dependências do projeto (executando mvn clean install)
RUN mvn clean install

# Exponha a porta em que o app vai rodar
EXPOSE 8080

# Comando para rodar o projeto (ajuste conforme necessário)
CMD ["java", "-jar", "target/your-app-name.jar"]

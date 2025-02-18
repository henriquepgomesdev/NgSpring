# Usando a imagem base do Maven
FROM maven:3.6.3-jdk-11 AS build

# Defina o diret�rio de trabalho para a pasta onde est� o pom.xml
WORKDIR /app/back-end

# Copie os arquivos da pasta back-end para o diret�rio de trabalho na imagem
COPY back-end/ /app/back-end

# Execute o Maven clean install no diret�rio onde est� o pom.xml
RUN mvn clean install

# Exponha a porta que o app vai rodar, se necess�rio
EXPOSE 8080

# Comando para rodar o aplicativo
CMD ["java", "-jar", "target/seu-arquivo.jar"]

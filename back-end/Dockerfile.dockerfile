# Usando a imagem base do Maven com JDK 17
FROM maven:3.8.4-openjdk-17 AS build

# Defina o diretório de trabalho para a pasta onde está o pom.xml
WORKDIR /app/back-end

# Copie os arquivos da pasta back-end para o diretório de trabalho na imagem
COPY / /app/back-end

# Execute o Maven clean install no diretório onde está o pom.xml
RUN mvn clean install

# Usar a imagem oficial do Tomcat como base
FROM tomcat:9.0

# Apagar qualquer configuração anterior de Tomcat (para limpar webapps)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar o arquivo .war para a pasta webapps do Tomcat e renomeá-lo para ROOT.war
COPY --from=build /app/back-end/target/estudos-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expõe a porta do Tomcat
EXPOSE 8090

# O Tomcat vai iniciar automaticamente ao rodar o contêiner
CMD ["catalina.sh", "run"]

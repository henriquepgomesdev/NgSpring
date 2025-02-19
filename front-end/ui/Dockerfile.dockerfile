# Etapa 1: Construir o projeto Angular
FROM node:18 AS build

# Define o diret�rio de trabalho dentro do container
WORKDIR /app

# Copia o package.json e o package-lock.json
COPY package*.json ./

# Instala as depend�ncias do Angular e a Angular CLI
RUN npm install
RUN npm install -g @angular/cli

# Copia o c�digo fonte para dentro do container
COPY . .

# Gera os arquivos de produ��o do Angular (sem --prod)
RUN ng build

# Etapa 2: Servir os arquivos com o Nginx
FROM nginx:alpine

# Remove o conte�do padr�o do Nginx
RUN rm -rf /usr/share/nginx/html/*

# Copia os arquivos gerados para a pasta do Nginx
COPY --from=build /app/dist/ui /usr/share/nginx/html

# Expondo a porta 80
EXPOSE 80

# Comando para rodar o Nginx
CMD ["nginx", "-g", "daemon off;"]

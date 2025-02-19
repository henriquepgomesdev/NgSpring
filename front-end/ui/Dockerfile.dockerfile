# Etapa 1: Construir o projeto Angular
FROM node:18 AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o package.json e o package-lock.json
COPY package*.json ./

# Instala as dependências do Angular
RUN npm install

# Copia o código fonte para dentro do container
COPY . .

# Gera os arquivos de produção do Angular (sem --prod)
RUN ng build

# Etapa 2: Servir os arquivos com o Nginx
FROM nginx:alpine

# Remove o conteúdo padrão do Nginx
RUN rm -rf /usr/share/nginx/html/*

# Copia os arquivos gerados para a pasta do Nginx
COPY --from=build /app/dist/seu-projeto-angular /usr/share/nginx/html

# Expondo a porta 80
EXPOSE 80

# Comando para rodar o Nginx
CMD ["nginx", "-g", "daemon off;"]

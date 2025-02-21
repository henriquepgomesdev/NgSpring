# Etapa 1: Construir o projeto Angular
FROM node:18 AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia apenas os arquivos necessários para instalar dependências
COPY package.json package-lock.json ./

# Instala as dependências do projeto
RUN npm install --legacy-peer-deps

# Copia o restante do código fonte
COPY . .

# Gera os arquivos de produção do Angular
RUN npm run build -- --configuration=production

# Etapa 2: Servir os arquivos com o Nginx
FROM nginx:alpine

# Remove o conteúdo padrão do Nginx
RUN rm -rf /usr/share/nginx/html/*

# Copia os arquivos gerados pelo Angular para a pasta do Nginx
COPY --from=build /app/dist/ui /usr/share/nginx/html

# Expondo a porta 80
EXPOSE 80

# Comando para rodar o Nginx
CMD ["nginx", "-g", "daemon off;"]
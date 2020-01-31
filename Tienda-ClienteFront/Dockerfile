# Stage 0, based on Node.js, to build and compile Angular
#Seleccionamos la ultima versión de node
FROM node:latest as node 
##Se crea una carpeta en el docker
WORKDIR /app
## se copian los archivos que están acá al docker
COPY ./ /app/
RUN npm install
ARG configuration=production
RUN npm run build -- --prod --configuration=$configuration

# Stage 1, based on Nginx, to have only the compiled app, ready for production with Nginx
FROM nginx:stable-alpine
COPY --from=node /app/dist/* /usr/share/nginx/html
COPY ./nginx-custom.conf /etc/nginx/conf.d/default.conf
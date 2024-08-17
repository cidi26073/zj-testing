FROM nginx:stable-alpine AS builder

WORKDIR /usr/src

RUN apk add --no-cache --virtual .build-deps \
    gcc \
    libc-dev \
    make \
    openssl-dev \
    pcre-dev \
    zlib-dev \
    linux-headers \
    libxslt-dev \
    gd-dev \
    geoip-dev \
    git

RUN wget "http://nginx.org/download/nginx-${NGINX_VERSION}.tar.gz" -O nginx.tar.gz
RUN tar -xzvf "nginx.tar.gz"

RUN git clone "https://github.com/openresty/echo-nginx-module.git"
RUN ECHODIR="/usr/src/echo-nginx-module"

RUN cd nginx-$NGINX_VERSION && \
    ./configure --add-module=$ECHODIR && \
    make install

CMD [ "ls", "-al", "/usr/src/nginx-1.24.0/objs" ]

# FROM nginx:stable-alpine

# COPY --from=builder /usr/src/nginx-${NGINX_VERSION}/objs/ngx_http_echo_module.so /usr/local/nginx/modules/ngx_http_echo_module.so

# WORKDIR /usr/src

# CMD ["nginx", "-g", "daemon off;"]
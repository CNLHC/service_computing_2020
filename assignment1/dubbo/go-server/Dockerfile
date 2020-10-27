from golang:1.15-alpine
WORKDIR /docker
COPY . .
RUN apk add --update alpine-sdk
RUN go env -w GO111MODULE=on
RUN go env -w GOPROXY=https://goproxy.cn,direct
RUN go build -o goserver
ENV CONF_PROVIDER_FILE_PATH=/docker/conf/server.yml
ENV APP_LOG_CONF_FILE=/docker/conf/log.yml
CMD /docker/goserver





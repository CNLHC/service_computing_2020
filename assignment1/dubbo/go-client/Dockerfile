from golang:1.15-alpine
WORKDIR /docker
COPY . .
RUN go env -w GO111MODULE=on
RUN go env -w GOPROXY=https://goproxy.cn,direct
RUN go build -o goclient

ENV CONF_CONSUMER_FILE_PATH=/docker/conf/client.yml
ENV CONF_PROVIDER_FILE_PATH=/docker/conf/client.yml
ENV APP_LOG_CONF_FILE=/docker/conf/log.yml
CMD sh ./test.sh





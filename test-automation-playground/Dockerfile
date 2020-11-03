FROM java:8-jdk-alpine
ADD . /code
WORKDIR /code
EXPOSE 7001 27017
CMD echo 127.0.0.1 mongodb >> /etc/hosts; supervisord -n;
CMD sleep 5 && java -jar /code/releases/test-automation-playground-1.0.0.jar
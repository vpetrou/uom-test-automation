docker rm demo-app --force
docker rm mongodb --force
docker-compose build
docker-compose up
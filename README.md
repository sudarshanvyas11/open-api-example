# open-api-example

# If the profile is set to mysql, this implementation may require a docker mysql container running as a prerequisite

# Some useful docker commands for MySql

docker pull mysql:latest

docker run -itd -p 6666:3306 --name=<<MYSQL_CONTAINER_NAME>> --env="MYSQL_ROOT_PASSWORD=<<YOUR_PASSWORD>>" --env="
MYSQL_DATABASE=book" mysql
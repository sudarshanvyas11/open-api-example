pipeline {
    agent{
        docker {
            image 'maven:3.8.7-eclipse-temurin-17'
            args '-v $HOME/.m2:/root/.m2'
        }
    }
    stages{
        stage('compile') {
            steps {
                sh '''
                    mvn -B -DskipTests clean compile
                   '''
            }
        }
        stage('test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('package') {
            steps {
                sh '''
                    mvn -B -DskipTests clean package
                    cp target/open-api-example-1.0-SNAPSHOT.jar /home/sudarshan/projects/open-api/open-api-example.jar
                    chmod 777 /home/sudarshan/projects/open-api/open-api-example.jar
                   '''
            }
        }
        stage('docker build') {
            steps {
                sh '''
                    docker pull mysql/mysql-server:latest
                    docker run --detach --name=open-api-mysql --env="MYSQL_TCP_PORT=3306" -d mysql/mysql-server:latest
                   '''
            }
        }
    }
}
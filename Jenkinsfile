pipeline {
    agent{
        docker {
            image 'maven:3.8.7-eclipse-temurin-17'
        }
    }
    stages{
        stage('build') {
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
                   '''
            }
        }
        stage('docker build') {
            steps {
                sh '''
                    /usr/bin/docker build -f Dockerfile -t open-api-app
                   '''
            }
        }
        stage('docker push') {
            steps {
                sh '''
                    /usr/bin/docker tag open-api-app sudarshanvyas/open-api-app
                    /usr/bin/docker push sudarshanvyas/open-api-app
                   '''
            }
        }
        stage('docker run') {
            steps {
                sh '''
                    /usr/bin/docker pull sudarshanvyas/open-api-app
                    /usr/bin/docker-compose down
                    /usr/bin/docker-compose up
                   '''
            }
        }
    }
}
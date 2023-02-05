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
        stage('docker publish') {
            steps {
                sh '''
                    docker build -f Dockerfile -t open-api-app
                    docker tag open-api-app sudarshanvyas/open-api-app
                    docker push sudarshanvyas/open-api-app
                   '''
            }
        }
        stage('docker deploy') {
            steps {
                sh '''
                    docker pull sudarshanvyas/open-api-app
                    docker-compose down
                    docker-compose up
                   '''
            }
        }
    }
}
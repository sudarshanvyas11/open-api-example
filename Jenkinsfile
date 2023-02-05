pipeline {
    agent{
        docker {
            image 'maven:3.8.7-eclipse-temurin-17'
            args '-v $HOME/.m2:/root/.m2'
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
                    docker build -f Dockerfile -t open-api-app
                   '''
            }
        }
        stage('docker push') {
                sh '''
                    docker tag open-api-app sudarshanvyas/open-api-app
                    docker push sudarshanvyas/open-api-app
                   '''
        }
        stage('docker run') {
                sh '''
                    docker pull sudarshanvyas/open-api-app
                    docker-compose down
                    docker-compose up
                   '''
        }
    }
}
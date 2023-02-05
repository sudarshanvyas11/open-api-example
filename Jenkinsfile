pipeline {
    agent none
    stages{
        stage('compile') {
            agent{
                docker {
                    image 'maven:3.8.7-eclipse-temurin-17'
                }
            }
            steps {
                sh '''
                    mvn -B -DskipTests clean compile
                   '''
            }
        }
        stage('test') {
            agent{
                docker {
                    image 'maven:3.8.7-eclipse-temurin-17'
                }
            }
            steps {
                sh '''
                    mvn test
                   '''
            }
        }
        stage('install') {
            agent{
                docker {
                    image 'maven:3.8.7-eclipse-temurin-17'
                }
            }
            steps {
                sh '''
                    mvn -B -DskipTests clean install
                   '''
            }
        }
        stage('docker build') {
            agent any
            steps {
                sh '''
                    docker build -f Dockerfile -t open-api-app .
                    docker tag open-api-app sudarshanvyas/open-api-app
                   '''
            }
        }
        stage('docker push') {
            agent any
            steps {
                    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'dockerhubPassword', usernameVariable: 'dockerhubUser')]) {
                    	sh "docker login -u ${env.dockerhubUser} -p ${env.dockerhubPassword}"
                        sh 'docker push sudarshanvyas/open-api-app'
                    }
            }
        }
        stage('docker deploy') {
            agent any
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
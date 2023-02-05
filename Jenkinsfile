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
                def dockerImage = docker.build("open-api-app:${env.BUILD_ID}", "-f Dockerfile")
                dockerImage.push()
                dockerImage.push('latest')
            }
        }
        stage('docker deploy') {
            steps {
                def dockerImage = docker.image("open-api-app:latest")
                dockerImage.pull()
                docker.compose.down()
                docker.compose.up()
            }
        }
    }
}
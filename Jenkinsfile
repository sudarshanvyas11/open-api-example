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
                    mvn -B -DskipTests clean package
                    cp target/open-api-example-1.0-SNAPSHOT.jar /home/sudarshan/projects/open-api/open-api-example.jar
                    chmod 777 /home/sudarshan/projects/open-api/open-api-example.jar
                   '''
            }
        }
        stage('test') {
                    steps {
                        sh 'mvn test'
                    }
                }
                stage('deploy') {
                    steps {
                        sh 'java -jar /home/sudarshan/projects/open-api/open-api-example.jar --spring.profiles.active=mysql &'
                    }
                }
    }
}
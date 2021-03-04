pipeline {

    agent any

    parameters{
        string(name:'IMAGE_NAME', defaultValue: 'java-calculator', description: 'Docker image name')
        string(name:'JAR_NAME', defaultValue: 'calculadora', description: '.jar file name')
    }

    stages{

        stage("Build Jar"){
            steps{
                sh 'javac *.java'
                sh 'jar cfe "$JAR_NAME".jar Calculator *.class'
            }
        }

        stage("Store artifact on Nexus"){
            steps{
                withCredentials([usernameColonPassword(credentialsId: 'nexus-user-credentials', variable: 'USERPASS')]) {
                    sh 'curl -v- u "$USERPASS" --upload-file /var/jenkins_home/workspace/calculator/'
                }
            }
        }

        stage("Create Docker Image"){
            steps{
                sh 'docker build -t "$IMAGE_NAME":v1.0 .'
            }
        }

        stage("Push Image to Nexus"){
            steps{
                withCredentials([usernamePassword(credentialsId: 'nexus-user-credentials', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
                sh 'docker login -u "$USERNAME" -p "$PASSWORD" localhost:8082'
                sh 'docker tag "$IMAGE_NAME":v1.0 localhost:8082/"$IMAGE_NAME":V1.0'
                sh 'docker push localhost:8082/"$IMAGE_NAME":v1.0'
                }
            }
        }

        stage("Clear Workspace"){
            steps{
                cleanWs()
            }
        }
    }
}
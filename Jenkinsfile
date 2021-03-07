pipeline {

    agent any

    parameters{
        string(name:'IMAGE_NAME', defaultValue: 'java-calculator', description: 'Docker image name')
        string(name:'JAR_NAME', defaultValue: 'calculadora', description: '.jar file name')
    }

    stages{

        stage("SonarQube Analysis"){            
            steps{
                script{    
                    def scannerhome = tool 'Sonar-Scanner';       
                    withSonarQubeEnv('sonarqube-server') {
                    sh """${scannerhome}/bin/sonar-scanner \
                    -D sonar.login=1a7946d402e9e7f2668a7b5ead2d2fead9134cb1 \
                    -D sonar.projectKey=calculator \
                    -D sonar.java.binaries=/var/jenkins_home/workspace/calculator \
                    -D sonar.java.source=11 \
                    -D sonar.host.url=http://sonarqube:9000"""
                    }
                }
            }
        }
        
        stage("Build Jar"){
            steps{
                sh 'javac *.java'
                sh 'jar cfe "$JAR_NAME".jar Calculator *.class'
            }
        }

        stage("Store artifact on Nexus"){
            steps{
                withCredentials([usernameColonPassword(credentialsId: 'nexus', variable: 'USERPASS')]) {
                    sh 'curl -v -u "$USERPASS" --upload-file /var/jenkins_home/workspace/calculator/"$JAR_NAME".jar http://nexus:8081/repository/artifact-repo/'
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
                withCredentials([usernamePassword(credentialsId: 'nexus', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
                sh 'docker login -u "$USERNAME" -p "$PASSWORD" http://localhost:8082'
                sh 'docker tag "$IMAGE_NAME":v1.0 localhost:8082/"$IMAGE_NAME":v1.0'
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
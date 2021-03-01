pipeline
{​​

    agent any

    parameters{
   ​​
        // Tem que se ir ao Jenkins > Configure > This project is parameterized. 

        string(name: 'DOCKER_IMAGE_NAME', defaultValue: 'image_name', description: 'Docker image name')

        string(name: 'DOCKER_CONTAINER_NAME', defaultValue: 'container_name', description: 'Docker container name')

        string(name: 'DOCKER_PORT', defaultValue: '3000', description: 'Docker port')

    }​​

     stages {​​

        stage('Stage docker build') {​​

            steps {​​

                sh "docker rmi -f ${​​DOCKER_IMAGE_NAME}​​"

                sh "docker build -t ${​​DOCKER_IMAGE_NAME}​​ ."

                sh "docker login -u admin -p admin localhost:8082"

                sh "docker tag ${​​DOCKER_IMAGE_NAME}​​ localhost:8082/${​​DOCKER_IMAGE_NAME}​​"

                sh "docker push localhost:8082/${​​DOCKER_IMAGE_NAME}​​"

                sh "javac *.java"

                sh "jar cfe calculator.jar Calculator *.class"

                sh "curl -v --user 'admin:admin' --upload-file calculator.jar http://nexus:8081/repository/my-raw/"

            }​​

        }​​


        // Apaga os dados do workspace.

        stage('Stage D - Clean up resources') {​​
            steps
            {​​
                cleanWs()
            }​​
        }​​
    }​​
}​​
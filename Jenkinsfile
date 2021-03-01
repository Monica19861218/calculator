pipeline

{​​

    agent {​​

        label "mvn"

    }​​

    parameters

    {​​

        // Tem que se ir ao Jenkins > Configure > This project is parameterized. 

        string(name: 'DOCKER_IMAGE_NAME', defaultValue: 'image_name', description: 'Docker image name')

        string(name: 'DOCKER_CONTAINER_NAME', defaultValue: 'container_name', description: 'Docker container name')

        string(name: 'DOCKER_PORT', defaultValue: '3000', description: 'Docker port')

    }​​



 

     stages

    {​​

        stage ('Stage maven')

        {​​

            steps

            {​​

                sh 'mvn clean package'

            }​​

        }​​

  

        stage('Stage docker build')

        {​​

            steps

            {​​

                sh "docker rmi -f ${​​DOCKER_IMAGE_NAME}​​"

                sh "docker build -t ${​​DOCKER_IMAGE_NAME}​​ ."

                sh "docker login -u admin -p admin localhost:8082"

                sh "docker tag ${​​DOCKER_IMAGE_NAME}​​ localhost:8082/${​​DOCKER_IMAGE_NAME}​​"

                sh "docker push localhost:8082/${​​DOCKER_IMAGE_NAME}​​"

                sh "javac *.java"

                sh "jar cfe calculator.jar TotalCalculator *.class"

                sh "curl -v --user 'admin:admin' --upload-file ./target/*.jar http://nexus:8081/repository/my-raw/"

            }​​

        }​​

 

        stage('Stage docker run')

        {​​

            steps

            {​​

                sh "docker rm -f ${​​DOCKER_CONTAINER_NAME}​​"

                sh "docker run -d -p ${​​DOCKER_PORT}​​:8080 --name ${​​DOCKER_CONTAINER_NAME}​​ ${​​DOCKER_IMAGE_NAME}​​"

            }​​

        }​​

 

        // Apaga os dados do workspace.

        stage('Stage D - Clean up resources')

        {​​

            steps

            {​​

                cleanWs()

            }​​

        }​​

        

    }​​

}​​
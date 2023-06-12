pipeline {
   agent {
        docker { image 'public.ecr.aws/docker/library/maven:3.9-sapmachine' }
    }

    tools {
        maven "maven"
    }
    
    stages {
        stage('Build') {
            steps {
                git branch: 'main', credentialsId: '74e542ae-bb2a-464a-b910-dc1eecd08124', url: 'https://github.com/OleksandrMelnyk4/selenide-allure-junit-rp.git'
				 sh "docker version"
                sh "mvn clean verify"
              
            }
        }
    }
}

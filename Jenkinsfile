pipeline {
    agent any

    tools {
        maven "maven",
        docker "docker"
    }
    
    stages {
        stage('Build') {
            steps {
       
                git branch: 'main', credentialsId: '74e542ae-bb2a-464a-b910-dc1eecd08124', url: 'https://github.com/OleksandrMelnyk4/selenide-allure-junit-rp.git'
				         sh "docker-compose –f src/resources/docker-compose.yml run –rm compile"
                sh "mvn clean verify"
              
            }
        }
    }
}

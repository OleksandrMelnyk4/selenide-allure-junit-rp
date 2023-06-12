pipeline {
    agent any

    tools {
        maven "maven"
    }
    
    stages {
      
         stage('Source') {
            steps {
             git branch: 'main',
             credentialsId: '74e542ae-bb2a-464a-b910-dc1eecd08124',
             url: 'https://github.com/OleksandrMelnyk4/selenide-allure-junit-rp.git',
             poll: true
            }
        }
        stage('Clean') {
            steps {
              sh "mvn clean"
            }
        }
        stage('Package') {
            steps {
              sh "mvn package -DskipTests"
            }
        }
    }
  
    post {
        always {
            script {
                if (fileExists('target/allure-results')) {
                    zip zipFile: 'allure-results.zip', archive: true, dir: 'target/allure-results'
                }
              
                archiveArtifacts allowEmptyArchive: true,
                artifacts: '**/.jar'
            }
        }
}
}

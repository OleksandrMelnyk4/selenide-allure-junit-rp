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
        stage('Test') {
            steps {
              bat "mvn clean test"
            }
        }
}
}

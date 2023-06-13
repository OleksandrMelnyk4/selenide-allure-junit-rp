pipeline {
    agent any

    tools {
      maven "maven"
    }

    stages {
      stage('Scan') {
        steps {
          withSonarQubeEnv(installationName: 'SonarQubeServers', envOnly: true)
          bat "mvn clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar"
        }
      }
      
      stage('Run test') {
        steps {
          bat "mvn clean test -Dcucumber.filter.tags=@Api"
        }
      }
      stage('Reports') {
        steps {
          script {
            allure([
              includeProperties:false,
              jdk:'',
              properties: [],
              reportBuildPolicy: 'ALWAYS',
              results: [[path:'target/allure-results']]
            ])
          }
        }
      }
    }
  }

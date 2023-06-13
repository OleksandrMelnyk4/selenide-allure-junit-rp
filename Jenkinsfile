pipeline {
    agent any

    tools {
      maven "maven"
    }

    stages {
      stage('Run test') {
        steps {
           withSonarQubeEnv('SonarQubeScanner')
          {
            bat 'mvn clean sonar:sonar'
            bat "mvn test -Dcucumber.filter.tags=@Api"
          }
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

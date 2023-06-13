pipeline {
  agent any
    tools {
      maven "maven"
    }

    stages {
       stage("SonarQube analysis") {
         steps {
            withSonarQubeEnv(installationName: 'SonarQubeServers', credentialsId: 'sonar_token') 
               {
                 bat "mvn clean sonar:sonar"
          }
         }
      }

     stage("Quality Gate") {
       steps {
          timeout(time: 2, unit: 'HOURS') {
            waitForQualityGate abortPipeline: true
              }
            }
          }
      
      stage('Run tests') {
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

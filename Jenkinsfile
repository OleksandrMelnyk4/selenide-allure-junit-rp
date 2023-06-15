pipeline {
  agent any
    tools {
      maven "maven"
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
      post{
        always{
            slackSend( channel: "#taf-interaction", token: "JenkinsCI", color: "good", message: "Build has finished")
        }
    }
  }

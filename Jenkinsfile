pipeline {
  agent any

    tools {
      maven "maven"
    }

    stages {
       stage("build & SonarQube analysis") {
         steps {
            withSonarQubeEnv(installationName: 'SonarQubeServers', credentialsId: 'sonar_token') 
               {
                 bat "mvn clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar"
          }
         }
      }
      
      
//      stage("Quality Gate") {
//        steps {
//           timeout(time: 2, unit: 'HOURS') {
//             waitForQualityGate abortPipeline: false
//               }
//             }
//           }
      
//       stage('Run test') {
//         steps {
//           bat "mvn clean test -Dcucumber.filter.tags=@Api"
//         }
//       }
//       stage('Reports') {
//         steps {
//           script {
//             allure([
//               includeProperties:false,
//               jdk:'',
//               properties: [],
//               reportBuildPolicy: 'ALWAYS',
//               results: [[path:'target/allure-results']]
//             ])
//           }
//         }
//       }
    }
  }

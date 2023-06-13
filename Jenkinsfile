pipeline {
    agent any
	
    tools {
        maven "maven"
    }
	
    stages {
        stage('Run test') {
            steps {
              bat "mvn clean test -Dcucumber.filter.tags=@Api"
          }
        }
    }
	
 post {
        always {
            junit allowEmptyResults: true, testResults: 'target/allure-results/*.json'

        
        }
    }
}

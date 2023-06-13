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
		
	stage('reports') {
      steps {
       script {
            allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']]
            ])
    }
    }
}
    }
	

}

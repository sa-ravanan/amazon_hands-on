pipeline {
    agent any

    stages {
        stage('Build & Run Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
        
        stage('Re-run Failed Tests'){
			when{
				expression {currentBuild.result == 'FAILURE'}
			}
			steps{
				bat 'mvn test -DsuiteXmlFile=test-output/testng-failed.xml'
			}
		}
    }
}
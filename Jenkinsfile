pipeline {
    agent any

    stages {
        stage('Build & Run Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
    }
}
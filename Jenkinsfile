pipeline {
    agent any

        stage('Build & Run Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
}
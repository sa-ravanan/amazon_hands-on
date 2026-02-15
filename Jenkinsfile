pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/sa-ravanan/amazon_hands-on.git'
            }
        }

        stage('Build & Run Tests') {
            steps {
                mvn clean test
            }
        }
    }
}
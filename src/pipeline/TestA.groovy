package pipeline

public void test() {
    pipeline {
        agent any
        stages {
            stage('Build') {
                echo "Build stage"
            }
            stage('Test') {
                echo "Test stage"
            }
            stage('Deploy') {
                echo "Deploy stage"
            }
        }
    }
}
return this


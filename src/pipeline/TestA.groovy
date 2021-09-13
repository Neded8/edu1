package pipeline

public void test(){
    pipeline {
        agent none
        stages {
            stage('Build') {
                steps {
                    echo 'Buils Stage'
                }
            }
            stage('Test') {
                steps {
                    echo 'Test stage'
                }
            }
            stage('Deploy') {
                steps {
                    echo 'Deploy stage'
                }
            }
        }
    }

return this

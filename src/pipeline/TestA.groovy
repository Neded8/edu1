package pipeline

public void test() {
    node("master"){
        stage("Build"){
            echo "Build stage"
        }

    }
}
//    pipeline{
//        agent any
//        stages {
//            stage('Build') {
//                steps{
//                    echo "Build stage"
//                }
//            }
//            stage('Test') {
//                steps{
//                    echo "Test stage"
//                }
//            }
//            stage('Deploy') {
//                steps{
//                    echo "Deploy stage"
//                }
//            }
//        }
//    }
//}

return this


package pipeline

public void test() {
    node("master"){
        stage("Build"){
            echo "Build stage"
        }
        stage("Second"){
            dir("some_folder"){
                writeFile(file:"file.txt",text:"some text")
                def line = readFile(file:"file.txt")
                echo line
            }
        }
        stage("Third stage"){
            if(isUnix()){
                sh "echo 'some text' > file2"
            }
            else{
                bat "echo 'some text' > file2"
            }
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


package pipeline

public void test(){
    node(any()){
        stage("Build"){
            echo "Build stage"
        }
        stage("Test"){
            echo "Test stage"
        }
        stage("Deploy"){
            echo "Deploy stage"
        }
    }
}

return this

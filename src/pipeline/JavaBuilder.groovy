package pipeline

private void cleanUp() {
    stage("clean home directory") {
        echo "delete current directory"
        deleteDir()
    }
}

private void getSourceCode(String repoURL, String branchName) {
    stage("checkout to git") {
        checkout changelog: true, poll: true, scm: [
                $class                           : 'GitSCM',
                branches                         : [[name: branchName]],
                doGenerateSubmoduleConfigurations: false,
                userRemoteConfigs                : [[url: repoURL]]]
    }

}

private void runMaven(command) {
    if (isUnix()) {
//        withEnv(["JAVA_HOME=OLOLOL"]){
        sh "mvn ${command}"
//        }

    } else {
        withEnv(["JAVA_HOME=C:\\Users\\vstup\\.jdks\\corretto-1.8.0_292"]){
        bat "mvn ${command}"
        }
    }
}

private void buildMaven() {
    stage("build") {
        runMaven("clean package")
    }
}

void run(String nodeName, String repoURL, String branchName, String spriteRepoURL) {
    node(nodeName) {
        cleanUp()
        getSourceCode(repoURL, branchName)
        bat "cd"
        dir("${env.WORKSPACE}/aQA"){
            bat "cd"
        }
//        getSourceCode(spriteRepoURL, branchName)
//        sh "pwd"
        buildMaven()




    }

}

return this


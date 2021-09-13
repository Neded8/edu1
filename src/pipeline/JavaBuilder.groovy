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
//        withEnv(["JAVA_HOME=OLOLOL"]){
        bat "mvn ${command}"
//        }
    }
}

private void buildMaven() {
    stage("build") {
        runMaven("clean package")
    }
}

void run(String nodeName, String repoURL, String branchName) {
    node(nodeName) {
        cleanUp()
        getSourceCode(repoURL, branchName)
        buildMaven()
    }

}

return this


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
        withEnv(["JAVA_HOME=C:\\Users\\vstup\\.jdks\\corretto-1.8.0_292"]) {
            bat "mvn ${command}"
        }
    }
}

private void updateSprite(){
    stage("Sprite update"){
        echo "[INFO] run bat file for move sprite"
        bat "C:\\Users\\vstup\\AppData\\Local\\Jenkins\\.jenkins\\workspace\\MoveHumanSprite.bat"
    }

}



private void buildMaven() {
    stage("Build") {
        runMaven("clean package")
    }
}



void runScript(String nodeName, String repoURL, String branchName) {
    List<SpecialClass> specialList = Arrays.asList(
            new SpecialClass("https://github.com/Neded8/HVSP-Sprites.git","master","Sprites/Human"),
            new SpecialClass("https://github.com/Neded8/HVSP-Sprites.git","master","Sprites/Human"),
            new SpecialClass("https://github.com/Neded8/HVSP-Sprites.git","master","Sprites/Human"))
    node(nodeName) {
        cleanUp()
        for (i = 0; i< specialList.size(); i++){
            dir(i){
                getSourceCode(specialList.get(i).sourceRepoURL,specialList.get(i).branchName)
            }
        }
        dir("${env.WORKSPACE}/HVSP") {
            getSourceCode(repoURL, branchName)
            updateSprite()
            buildMaven()
        }
    }
}


return this


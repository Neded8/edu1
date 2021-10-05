package pipeline


private void cleanUp() {
    stage("clean home directory") {
        echo "delete current directory"
        deleteDir()
    }
}

private void getSourceCode(String repoURL, String branchName) {
    stage("checkout from git") {
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

//private void updateSprite(Collection<SpecialClass> specialList) {
//    stage("Sprite update") {
//        echo "[INFO] run bat file for move sprite"
//        for (def obj in specialList){
//            withEnv(["SOURCE_FOLDER=${env.WORKSPACE}\\source"]){
//                bat obj.copyScript
//            }
//
//        }
////        bat "C:\\Users\\vstup\\AppData\\Local\\Jenkins\\.jenkins\\workspace\\MoveHumanSprite.bat"
//    }
//
//}


private void buildMaven() {
    stage("Build") {
        runMaven("clean package")
    }
}


void runScript(String nodeName, String repoURL, String branchName, Collection<SpecialClass> specialList) {
    node(nodeName) {
        cleanUp()
        dir("source") {
            for (def i in env){
                env.getEnvironment().each { name, value -> println "Name: $name -> Value $value" }
            }
            getSourceCode(repoURL, branchName)
        }
        getAssets(specialList)
        dir("source") {
            buildMaven()
        }
    }
}

private void getAssets(Collection<SpecialClass> specialList) {
    stage("getAssets") {
        def i = 0
        for (def obj in specialList) {
            dir("assets/${i}") {
                getSourceCode(obj.sourceRepoURL, obj.branchName)
                withEnv(["SOURCE_FOLDER=${env.WORKSPACE}\\source\\src\\main\\resources\\Sprites\\"], "MAPPING_SCRIPT_FOLDER=${env.WORKSPACE}") {
                    def externalMethod = load("%MAPPING_SCRIPT_FOLDER%\\${obj.copyScript}")
                    externalMethod.readJson(obj.jsonFileName)

                }


            }
            i++
        }

    }
}


return this


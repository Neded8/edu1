package pipeline

class SpecialClass {
    String sourceRepoURL
    String branchName
    String copyScript
    String jsonFileName

    SpecialClass(String sourceRepoURL, String branchName, String copyScript = "copy.bat", String jsonFileName = "MappingData.txt") {
        this.sourceRepoURL = sourceRepoURL
        this.branchName = branchName
        this.copyScript = copyScript
        this.jsonFileName = jsonFileName
    }

}

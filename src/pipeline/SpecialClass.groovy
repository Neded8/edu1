package pipeline

class SpecialClass {
    String sourceRepoURL
    String branchName
    String copyScript

    SpecialClass(String sourceRepoURL, String branchName, String folderPath) {
        this.sourceRepoURL = sourceRepoURL
        this.branchName = branchName
        this.copyScript = folderPath
    }

}

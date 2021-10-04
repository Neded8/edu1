package pipeline

class SpecialClass {
    String sourceRepoURL
    String branchName
    String copyScript

    SpecialClass(String sourceRepoURL, String branchName, String copyScript) {
        this.sourceRepoURL = sourceRepoURL
        this.branchName = branchName
        this.copyScript = copyScript
    }

}

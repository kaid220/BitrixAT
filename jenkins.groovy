task_branch="${jenkinsBranch_name}"
testUserPassword1="${jenkinsTestUserPassword1}"
testUserPassword2="${jenkinsTestUserPassword2}"

def currentBranch = task_branch.contains("origin")?task_branch.split('/')[1]:task_branch.trim()
currentBuild.displayName = "$currentBranch"

node{
    withEnv("branch=$currentBranch"){
        stage("Checkout Branch"){
            id()
        }
    }
}
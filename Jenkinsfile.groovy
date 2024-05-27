task_branch="${jenkinsBranch_name}"
testUserPassword1="${jenkinsTestUserPassword1}"
testUserPassword2="${jenkinsTestUserPassword2}"
remote="${isRemote}"

def currentBranch = task_branch.contains("origin")?task_branch.split('/')[1]:task_branch.trim()
currentBuild.displayName = "$currentBranch"

node{
    withEnv("branch=$currentBranch"){
        stage("Build"){
            echo "Ветка сборки = '$currentBranch', приступаем к клонированию репозитория"
            git url: 'https://github.com/kaid220/BitrixAT.git'
        }
    }
}


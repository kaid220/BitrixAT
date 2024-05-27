pipline {
        agent any
        parameters {
                string(name: 'jenkinsBranch_name')
                string(name: 'jenkinsTestUserPassword1')
                string(name: 'jenkinsTestUserPassword2')
                string(name: 'isRemote')
        }
        stages {
                stage('Checkout to branch') {
                        steps {
                                script {
                                        def currentBranch = sh(script: "git rev-parse --abbrev-ref HEAD", returnStdout: true).trim
                                        def targetBranch = "${params.jenkinsBranch_name}"
                                        echo "Текущая ветка = '$currentBranch'"
                                        echo "Необходимая ветка = '$targetBranch'"
                                        if (currentBranch != targetBranch) git branch: targetBranch, url: "https://github.com/kaid220/BitrixAT.git"
                                }
                        }
                }
                stage('LocalSetting Creator') {
                        steps {
                                script {
                                        echo "Запущено создание файла localSetting.properties"
                                        writeFile file: 'localSetting.properties', text: """
                    userLogin1 = xifural75@yandex.ru
                    userPassword1 =${params.jenkinsTestUserPassword1}
                    userLogin2 = xifural76@yandex.ru
                    userPassword2 =${params.jenkinsTestUserPassword2}
                    baseurl=https://b24-wqf2lv.bitrix24.ru
                    dataBaseUserName=root
                    dataBaseRootPassword=rootPassword
                    isRemote=${params.isRemote}"""
                                        sh 'cat localSetting.properties'
                                }
                        }
                }
        }
}
pipeline {
        agent any
        parameters {
                string(name: 'jenkinsTestUserPassword1')
                string(name: 'jenkinsTestUserPassword2')
                string(name: 'isRemote')
        }
        stages {
                stage('LocalSetting Creator') {
                        steps {
                                script {
                                        echo "Start creating localSetting.properties"
                                        writeFile file: 'src\\test\\resources\\localSetting.properties', text: """
                    userLogin1 = xifural75@yandex.ru
                    userPassword1 =${params.jenkinsTestUserPassword1}
                    userLogin2 = xifural76@yandex.ru
                    userPassword2 =${params.jenkinsTestUserPassword2}
                    baseurl=https://b24-wqf2lv.bitrix24.ru
                    dataBaseUserName=root
                    dataBaseRootPassword=rootPassword
                    isRemote=${params.isRemote}"""
                                        archiveArtifacts 'src\\test\\resources\\localSetting.properties'
                                        bat 'type src\\test\\resources\\localSetting.properties'
                                }
                        }
                }

                stage('Run tests') {
                        steps {
                                script {
                                        echo "Start all tests running"
                                        catchError(buildResult:'SUCCESS', stageResult: 'FAILURE') { bat returnStdout: true, script: 'gradlew.bat test'}
                                       // archiveArtifacts  'build\\reports\\tests\\test\\**'
                                }
                        }
                }
                stage('Run failed tests'){

                       /* when{
                                previousStageFailed()
                        }*/
                        steps {
                                script {
                                        echo "Run failed tests"
                                        catchError(buildResult:'SUCCESS', stageResult: 'FAILURE'){  bat returnStdout: true, script: 'gradlew.bat failedTests'}
                                        archiveArtifacts  'build\\reports\\tests\\test\\**'
                                }
                        }
                }

        }
        post{
                always{
                        echo 'Pipeline is complete'
                        echo """subject: "BitrixAT number of build [${BUILD_NUMBER}] ",
                                body:" allure-report: "<a href='${BUILD_URL}allure/'>${JOB_NAME} [${BUILD_NUMBER}]</a>"</p>"",
                                to: "xifural75@yandex.ru" """
                        emailext (
                                subject: "BitrixAT number of build [${BUILD_NUMBER}]",
                                body:""" allure-report: "<a href='${BUILD_URL}allure/'>${JOB_NAME} [${BUILD_NUMBER}]</a>"</p>"",""",
                                to: "xifural75@yandex.ru"
                        )
                }
        }
}

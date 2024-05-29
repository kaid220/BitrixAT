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

                stage('Running') {
                        steps {
                                script {
                                        echo "Start autotests"
                                        catchError(buildResult:'SUCCESS', stageResult: 'FAILURE') {
                                                def testResults = bat returnStdout: true, script: 'gradlew.bat test'
                                                echo testResults
                                        }
                                }
                        }
                }
                stage('Reports'){
                        steps{
                                allure([includeProperties: true,
                                        jdk: '',
                                        properties: [],
                                        reportBuildPolicy: 'ALWAYS',
                                        results: [[path: '**/allure-results']]
                                ])
                        }
                }
        }
        post{
                always{
                        echo 'Pipeline is complete'
                        emailext (
                                subject: "BitrixAT Отчет прогона тестов [${env.BUILD_NUMBER}] ",
                                body:"""Подробный allure-отчет: "<a href='${env.BUILD_URL}allure/'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
                                to: "xifural75@yandex.ru"
                        )
                }
        }
}
// vars/redisDeployment.groovy

def call(Map config) {
    pipeline {
        agent any
        stages {
            stage('Clone Repository') {
                steps {
                    script {
                        git url: config.repositoryUrl, branch: config.branch
                    }
                }
            }
            stage('User Approval') {
                steps {
                    input message: 'Approve to proceed with Redis deployment?', ok: 'Deploy'
                }
            }
            stage('Playbook Execution') {
                steps {
                    script {
                        ansiblePlaybook(
                            playbook: config.playbookPath,
                            inventory: config.inventoryPath,
                            extraVars: "redis_config=${config.configFile}"
                        )
                    }
                }
            }
            stage('Notification') {
                steps {
                    script {
                        notifySlack(
                            channel: config.notificationChannel,
                            message: "Redis deployment complete: ${env.BUILD_URL}"
                        )
                    }
                }
            }
        }
    }
}


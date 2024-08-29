package org.mycompany

class Ansible {
    static void runPlaybook(String playbookPath, String inventoryPath) {
        pipeline {
            agent any
            stages {
                stage('Checkout') {
                    steps {
                        checkout scm
                    }
                }
                stage('Run Ansible Playbook') {
                    steps {
                        sh "ansible-playbook -i ${inventoryPath} ${playbookPath} --private-key=ec2.pem"
                    }
                }
            }
        }
    }
}


def call() {
    stage('user approval') {

     input message: 'Do you want to proceed with the execution of Ansible Playbook ?', ok: 'Proceed'
    }
}

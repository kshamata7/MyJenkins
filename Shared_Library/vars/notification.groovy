def call() {
    stage('notify') {
          emailext body: 'Success', subject: 'Status', to: 'kshamata@gmail.com'
          slackSend channel: '#Jenkins Redis', message: 'Success '
    }
}

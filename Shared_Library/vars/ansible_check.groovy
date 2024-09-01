def call() {
    stage('Clone Repository') {
        git branch: 'main', url: 'https://github.com/kshamata7/MyJenkins.git'
    }
}

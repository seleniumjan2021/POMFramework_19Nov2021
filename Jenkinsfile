pipeline {
    agent any

    stages {
        stage('Smoke') {
            steps {
                build 'OrangeHRM_Smoke'
            }
        }
        stage('Sanity') {
            steps {
               build 'OrangeHRM_Sanity'
            }
        }
         stage('Regression') {
            steps {
                build 'OrangeHRM_Regression'
            }
        }
    }
}

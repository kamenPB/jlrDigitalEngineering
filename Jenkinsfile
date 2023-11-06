pipeline {
    agent any
    tools {
        maven "maven_3_9_5"
    }

    stages {
        stage ("Build Maven") {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/kamenPB/jlrDigitalEngineering']])
                sh "mvn clean install"
            }
        }
        stage ("Build Image") {
            steps {
                script {
                    sh "docker build -t jlr_image --load ."
                }
            }
        }
        stage ("Docker Login") {
            steps {
                script {
                    withCredentials([string(credentialsId: 'docker_pwd', variable: 'docker_pwd')]) {
                        sh "docker login -u kamen981 -p ${docker_pwd}"
                    }
                }
            }
        }
        stage ("Push Image to Hub") {
            steps {
                script {
                    sh "docker tag jlr_image kamen981/images:jlr_image"
                    sh "docker push kamen981/images:jlr_image"
                }
            }
        }
        // stage ("Run Image") {
        //     steps {
        //         script {
        //             sh "docker run -p 8181:8181 kamen981/images:jlr_image"
        //         }
        //     }
        // }
    }
}
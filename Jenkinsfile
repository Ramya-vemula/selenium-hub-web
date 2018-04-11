#!/usr/bin/env groovy
import static groovy.json.JsonOutput.*

properties([
        parameters([
                choice(name: 'browserName', choices: 'chrome', description: 'Select a browser'),
                choice(name: 'environment', choices: 'dev\nstaging\nprod', description: 'Select a environment')
        ]),
        disableConcurrentBuilds()
])

def gitUrl = "git@github.com:Rungway/selenium-web.git"

ansiColor('xterm') {
    node("Mac") {
        stage('Checkout') {

            checkout scm

            if (environment != null) {
            echo "Selected environment: ${environment}"
            }

            else {
              echo "NOT Selected environment to test"
              throw "NOT Selected environment to test"
              }
        }

        stage('Tests') {
            try {
                sh "gradle clean test"
             } catch (error) {
                  generateReport()
                  throw error
             }
        }

        stage('Report') {
             generateReport()
             }
    }
}

def generateReport() {
    script {
        allure([
                includeProperties: false,
                jdk              : '',
                properties       : [],
                reportBuildPolicy: 'ALWAYS',
                results          : [[path: 'build/allure-results']]
        ])
    }
}
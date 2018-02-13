#!/usr/bin/env groovy
import static groovy.json.JsonOutput.*

properties([
        parameters([
                choice(name: 'browserName', choices: 'chrome', description: 'Select a browser'),
                choice(name: 'platformName', choices: 'Mac', description: 'Select a platform')
        ]),
        disableConcurrentBuilds()
])

def gitCredentialId = "f6359ce2-6a4b-4b13-ad94-f2400f9dbe11"
def gitUrl = "git@bitbucket.org:rungway/selenium-web.git"

ansiColor('xterm') {
    node("Mac") {
        stage('Checkout') {
            checkout scm
        }
        stage('Tests') {
            try {
                sh "mvn clean test"
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
                results          : [[path: 'allure-results']]
        ])
    }
}
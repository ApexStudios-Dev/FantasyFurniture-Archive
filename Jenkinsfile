#!/usr/bin/env groovy

pipeline {
    agent any
    tools {
        jdk 'Java 17'
    }
    stages {
        stage('Setup Repo') {
            steps {
                echo 'Setting up local repo'
                sh 'git fetch --prune --tags'
            }
        }
        stage('Clean') {
            steps {
                echo 'Cleaning Project'
                sh 'rm -rf ./jars'
                sh 'chmod +x gradlew'
                sh './gradlew clean'
            }
        }
        stage('Build') {
            steps {
                echo 'Generating Resources (Fabric)'
                sh './gradlew fabric:runData'

                // echo 'Generating Resources (NeoForge)'
                // sh './gradlew neoforge:runData'

                echo 'Generating changelog files'
                sh './gradlew generateChangelogs'

                echo 'Building'
                sh './gradlew build'

                echo 'Compiling jars'
                sh './gradlew collectJars'
            }
        }
        stage('Publish') {
            // only publish if running on origin master branches
            // and on a tagged build
            when {
                allOf {
                    branch 'origin/**/master'
                    buildingTag()
                }
            }
            steps {
                withCredentials([string(credentialsId: 'changelog_server_key', variable: 'APEXSTUDIOS_CHANGELOG_SERVER_KEY')]) {
                    echo 'Uploading Changelog'
                    sh './gradlew publishChangelogFile'
                }

                withCredentials([
                    string(credentialsId: 'curseforge_token', variable: 'CURSEFORGE_TOKEN'),
                    string(credentialsId: 'modrinth_token', variable: 'MODRINTH_TOKEN')
                ]) {
                    echo 'Publishing to CurseForge & Modrinth'
                    sh './gradlew publishMods'
                }

                withCredentials([string(credentialsId: 'covers1624_maven_password', variable: 'MAVEN_PASSWORD')]) {
                    echo 'Publishing to Maven'
                    sh './gradlew publishReleasePublicationToReleasesRepository'
                }

                // withCredentials([string(credentialsId: 'discord_changelog_webhook_test', variable: 'DISCORD_CHANGELOG_WEBHOOK_URL')]) {
                withCredentials([string(credentialsId: 'discord_changelog_webhook_url', variable: 'MAVEN_PASSWORD')]) {
                    echo 'Notifying Discord'
                    sh './gradlew sendDiscordChangelog'
                }
            }
        }
        stage('Finalization') {
            steps {
                withCredentials([string(credentialsId: 'covers1624_maven_password', variable: 'MAVEN_PASSWORD')]) {
                    echo 'Publishing to Maven'
                    sh './gradlew publishSnapshotPublicationToSnapshotsRepository'
                }

                echo 'Archiving Jars'
                archiveArtifacts artifacts: 'jars/*.jar', fingerprint: true, followSymlinks: false, onlyIfSuccessful: true
            }
        }
    }
}
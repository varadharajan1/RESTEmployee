node('master') {
	checkout scm
	stage('build') {
		withMaven(jdk: 'JDK1.8', maven: 'Maven3') {
			bat 'mvn clean install'
		}
	}
}

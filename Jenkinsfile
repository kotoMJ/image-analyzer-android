#!groovy

pipeline {

	agent {
		label 'android'
	}

	options {
		buildDiscarder(logRotator(numToKeepStr: '30'))
		disableConcurrentBuilds()
		timeout(time: 1, unit: 'HOURS')
		timestamps()
		skipDefaultCheckout()
	}


	parameters {
		choice(
				name: 'environment',
				choices: 'mock\ndev\nproduction',
				description: 'Environment for build'
		)
		choice(
				name: 'build_type',
				choices: 'release\ndebug',
				description: 'Type of build'
		)
		choice(
				name: 'code_branch',
				choices: 'develop\nmaster\n',
				description: 'Environment for build'
		)
		string(name: 'slack_channel', defaultValue: '#image-analyzer-android', description: 'Slack Channel to send notifications to')
	}

	tools {
		gradle 'gradle_4.0'
	}
	environment {
		slack_channel = "${params.slack_channel}"
		build_variant = "${params.environment.capitalize()}${params.build_type.capitalize()}"
		code_branch = "${params.code_branch}"
		build_allowed = true
	}

	stages {
		stage("SCM") {
			steps {
				script {
					if (env.BRANCH_NAME) code_branch = env.BRANCH_NAME
				}
				echo "deleteDir..."
				deleteDir()
				echo "checkout..."
				checkout scm
				checkout changelog: false,
						poll: false,
						scm: [
								$class                           : 'GitSCM',
								branches                         : [
										[name: "${code_branch}"]
								],
								doGenerateSubmoduleConfigurations: false,
								extensions                       : [
										[$class: 'LocalBranch', localBranch: "**"],
										[$class: 'WipeWorkspace'],
										[$class             : 'SubmoduleOption',
										 disableSubmodules  : false,
										 parentCredentials  : false,
										 recursiveSubmodules: true,
										 reference          : '',
										 trackingSubmodules : false]
								],
								submoduleCfg                     : [],
								userRemoteConfigs                : [
										[
												credentialsId: 'USE_YOUR_CREDENTIALS_ID_HERE',
												url          : 'https://github.com/kotomisak/image-analyzer-android.git'
										]
								]
						]
				script {
					result = sh(script: "git log -1 | grep '\\[ci skip\\]'", returnStatus: true)
					if (result == 0) {
						build_allowed = false
					}
				}

				echo "BUILD ALLOWED set to ${build_allowed}"
			}
		}

		stage("Cleaning") {
			when {
				expression {return build_allowed}
			}
			steps {
				echo "Cleaning up..."
				sh './gradlew clean'
			}
		}

		stage("UnitTestingWithCoverage") {
			when {
				expression {build_allowed}
			}
			steps {
				echo "Code coverage cleanup ${WORKSPACE}..."
				dir('jacoco') {
					deleteDir()
				}
				echo "Code coverage computation for Env=${params.environment} and BuildType=${params.build_type} ..."
				sh "./gradlew module-core:jacocoUnitTestReport -PjacocoEnv=${params.environment} -PjacocoBuildType=${params.build_type}"
				sh "./gradlew module-face-detection:jacocoUnitTestReport -PjacocoEnv=${params.environment} -PjacocoBuildType=${params.build_type}"
				sh "./gradlew module-text-recognition:jacocoUnitTestReport -PjacocoEnv=${params.environment} -PjacocoBuildType=${params.build_type}"
				sh "./gradlew mobile:jacocoUnitTestReport -PjacocoEnv=${params.environment} -PjacocoBuildType=${params.build_type}"

				//It's required to repeat all patterns which are defined in jacoco.gradle for analyzer. Plugin shows incorrect results otherwise.
				jacoco(execPattern: '**/jacocoAllModules.exec',
						sourcePattern: "**/src/main/java/**/*,**/src/server/java/**/*,**/src/${params.build_type}/java/**/*",
						classPattern: '**/tmp/kotlin-classes/**/*,**/intermediates/classes/**/*',
						exclusionPattern: '**/com/example/**/*$*,**/com/example/**/databinding/*,**/com/example/**/di/*,**/com/example/**/rxfingerprint/*,**/com/example/**/encryption/*,**/com/example/**/glide/*,**/**/*UnitTest*,**/**/*_Factory*',
						inclusionPattern: '**/com/example/**/*'
				)
			}

			post {
				always {
					echo 'Publishing test results'
					archive "**/test-results/**/*"
					junit '**/test-results/**/*.xml'
					step([$class: 'Publisher'])
				}
			}
		}

		stage("AssembleAPK") {
			when {
				expression {return build_allowed}
			}
			steps {
				echo "Assembling with build_variant=${build_variant} ..."

				sh "./gradlew assemble${build_variant}"
			}
		}

		stage("Notify") {
			when {
				expression {return build_allowed}
			}
			steps {
				echo "Sending notifications"
			}
			post {
				always {
					sendNotifications currentBuild.result
				}
			}
		}
	}

} //end of pipeline

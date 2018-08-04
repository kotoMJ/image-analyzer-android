#!/usr/bin/env bash

./gradlew clean

# default code coverage
#./gradlew clean legalzoom-base:testDevDebug legalzoom-base:createDevDebugCoverageReport

# Kotlin specific codeCoverage with filtering


./gradlew legalzoom-base:jacocoTestReport -PjacocoBuildType=debug -PjacocoEnv=dev
if [[ "$OSTYPE" == "darwin"* ]]; then   # Mac OSX
/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --kiosk ./legalzoom-base/build/reports/jacocoTestReport/html/index.html
fi

./gradlew legalzoom-consultation:jacocoTestReport -PjacocoBuildType=release -PjacocoEnv=dev
if [[ "$OSTYPE" == "darwin"* ]]; then   # Mac OSX
/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --kiosk ./legalzoom-consultation/build/reports/jacocoTestReport/html/index.html
fi

./gradlew legalzoom-genesyschat:jacocoTestReport -PjacocoBuildType=release -PjacocoEnv=dev
if [[ "$OSTYPE" == "darwin"* ]]; then   # Mac OSX
/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --kiosk ./legalzoom-genesyschat/build/reports/jacocoTestReport/html/index.html
fi

./gradlew legalzoom-login:jacocoTestReport -PjacocoBuildType=release -PjacocoEnv=dev
if [[ "$OSTYPE" == "darwin"* ]]; then   # Mac OSX
/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --kiosk ./legalzoom-login/build/reports/jacocoTestReport/html/index.html
fi

./gradlew legalzoom-settings:jacocoTestReport -PjacocoBuildType=release -PjacocoEnv=dev
if [[ "$OSTYPE" == "darwin"* ]]; then   # Mac OSX
/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --kiosk ./legalzoom-settings/build/reports/jacocoTestReport/html/index.html
fi

./gradlew legalzoom-tracker:jacocoTestReport -PjacocoBuildType=release -PjacocoEnv=dev
if [[ "$OSTYPE" == "darwin"* ]]; then   # Mac OSX
/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --kiosk ./legalzoom-tracker/build/reports/jacocoTestReport/html/index.html
fi

./gradlew legalzoom-urbanairship:jacocoTestReport -PjacocoBuildType=release -PjacocoEnv=dev
if [[ "$OSTYPE" == "darwin"* ]]; then   # Mac OSX
/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --kiosk ./legalzoom-urbanairship/build/reports/jacocoTestReport/html/index.html
fi

./gradlew legalzoom-vault:jacocoTestReport -PjacocoBuildType=release -PjacocoEnv=dev
if [[ "$OSTYPE" == "darwin"* ]]; then   # Mac OSX
/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --kiosk ./legalzoom-vault/build/reports/jacocoTestReport/html/index.html
fi

./gradlew mobile:jacocoTestReport -PjacocoBuildType=release -PjacocoEnv=dev
if [[ "$OSTYPE" == "darwin"* ]]; then   # Mac OSX
/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --kiosk ./mobile/build/reports/jacocoTestReport/html/index.html
fi
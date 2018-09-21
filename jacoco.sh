#!/usr/bin/env bash

./gradlew clean

# Kotlin specific codeCoverage with filtering

./gradlew module-core:jacocoTestReport -PjacocoBuildType=debug -PjacocoEnv=dev
if [[ "$OSTYPE" == "darwin"* ]]; then   # Mac OSX
/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --kiosk ./module-core/build/reports/jacocoTestReport/html/index.html
fi

./gradlew module-face-detection:jacocoTestReport -PjacocoBuildType=release -PjacocoEnv=dev
if [[ "$OSTYPE" == "darwin"* ]]; then   # Mac OSX
/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --kiosk ./module-face-detection/build/reports/jacocoTestReport/html/index.html
fi

./gradlew module-text-recognition:jacocoTestReport -PjacocoBuildType=release -PjacocoEnv=dev
if [[ "$OSTYPE" == "darwin"* ]]; then   # Mac OSX
/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --kiosk ./module-text-recognition/build/reports/jacocoTestReport/html/index.html
fi

./gradlew mobile:jacocoTestReport -PjacocoBuildType=release -PjacocoEnv=dev
if [[ "$OSTYPE" == "darwin"* ]]; then   # Mac OSX
/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --kiosk ./mobile/build/reports/jacocoTestReport/html/index.html
fi
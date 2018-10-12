#!/usr/bin/env bash

./gradlew clean

# Kotlin specific codeCoverage with filtering

./gradlew module-core:jacocoUnitTestReport -PjacocoBuildType=debug -PjacocoEnv=dev
if [[ "$OSTYPE" == "darwin"* ]]; then   # Mac OSX
/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --kiosk ./module-core/build/reports/jacocoUnitTestReport/html/index.html
fi

./gradlew module-face-detection:jacocoUnitTestReport -PjacocoBuildType=debug -PjacocoEnv=dev
if [[ "$OSTYPE" == "darwin"* ]]; then   # Mac OSX
/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --kiosk ./module-face-detection/build/reports/jacocoUnitTestReport/html/index.html
fi

./gradlew module-text-recognition:jacocoUnitTestReport -PjacocoBuildType=debug -PjacocoEnv=dev
if [[ "$OSTYPE" == "darwin"* ]]; then   # Mac OSX
/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --kiosk ./module-text-recognition/build/reports/jacocoUnitTestReport/html/index.html
fi

./gradlew mobile:jacocoUnitTestReport -PjacocoBuildType=debug -PjacocoEnv=dev
if [[ "$OSTYPE" == "darwin"* ]]; then   # Mac OSX
/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --kiosk ./mobile/build/reports/jacocoUnitTestReport/html/index.html
fi
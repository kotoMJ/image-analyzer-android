# image-analyzer-android
Sample project to show how compute code coverage of tests on multi-module Android project written in Kotlin.

App it self contains several business modules to provide Google MLKit functionality.


| Branch | Status on CircleCI | Status on TravisCI | Code coverage |
| --- | --- | --- | --- |
| develop| [![CircleCI](https://circleci.com/gh/kotomisak/image-analyzer-android.svg?style=svg)](https://circleci.com/gh/kotomisak/image-analyzer-android)|[![Build Status](https://travis-ci.org/kotomisak/image-analyzer-android.svg?branch=develop)](https://travis-ci.org/kotomisak/image-analyzer-android)|[![codecov](https://codecov.io/gh/kotomisak/image-analyzer-android/branch/develop/graph/badge.svg)](https://codecov.io/gh/kotomisak/image-analyzer-android)|


# Android MeetUp presentation

Look at slides from my talk on Android meetup:<br/>
[ ![STRV](./extras/presentation/coverage_logo.png) ](./extras/presentation/AndroidCodeCoverage.pdf)<br/>


# Code Schema

Codebase is written using STRV code schema.

Preferences -> Editor -> Codestyle -> Kotlin -> `extras/code-schema/strv.xml`

# Run application from source

`Use mockDebug Build Variant.`

Use **MOCK** flavour to run application without need to register app to fabric.<br/>
Use **DEBUG** variant to run application without need to configure app signing.<br/>

# Run Code Coverage locally

Run `sh jacoco.sh` in the root of project.
If you run it on MacOs it will open Chrome and display generated HTMLs.<br/>

Otherwise you will find html files in build directories:<br/>
./module-core/build/reports/jacocoUnitTestReport/html/index.html<br/>
./module-face-detection/build/reports/jacocoUnitTestReport/html/index.html<br/>
./module-text-recognition/build/reports/jacocoUnitTestReport/html/index.html<br/>
./mobile/build/reports/jacocoUnitTestReport/html/index.html<br/>

# UI TEST COVERAGE still in progress

I have been working on adding UI testing for isolated fragments.
Then I would like to connect code coverage also for that UI tests.
Every work in progress is available in feature branches.

# Special thanks to sources


[Multi module navigation with the Android Architec¬ture component](https://medium.com/@hartwich.daniel/multi-module-navigation-with-the-android-architecture-component-82ed028fa1d9)

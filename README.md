# image-analyzer-android
Sample project to show how compute code coverage of tests on multi-module Android project written in Kotlin.

App it self contains several business modules to provide Google MLKit functionality.


| Branch | Status on CircleCI | Status on TravisCI | Code coverage |
| --- | --- | --- | --- |
| develop| [![CircleCI](https://circleci.com/gh/kotomisak/image-analyzer-android.svg?style=svg)](https://circleci.com/gh/kotomisak/image-analyzer-android)|[![Build Status](https://travis-ci.org/kotomisak/image-analyzer-android.svg?branch=develop)](https://travis-ci.org/kotomisak/image-analyzer-android)|[![codecov](https://codecov.io/gh/kotomisak/image-analyzer-android/branch/develop/graph/badge.svg)](https://codecov.io/gh/kotomisak/image-analyzer-android)|


# configure your secrets

## crashlytics
Crashlytics api_key is kept in **local.properties**
Create key/value pair in **local.properties** `crashlytics_key=your_api_key_here`
You can keep this key also as environtment variable (e.g. for usage in CI)

# Special thanks to sources


[Multi module navigation with the Android Architec¬ture component](https://medium.com/@hartwich.daniel/multi-module-navigation-with-the-android-architecture-component-82ed028fa1d9)

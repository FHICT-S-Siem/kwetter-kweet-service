# kwetter-kweet-service

This service provides REST calls to Create, Read, Update and Delete Kweets (Tweets).

## GitHub Actions - Building Gradle CI
Building the CI with Gradle requires an additional step, making a gradlew executable.

```
jobs:
  build:
    name: Build
    runs-on: ubuntu-latest
    environment: Build
    steps:
    - uses: actions/checkout@v2
    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
         java-version: '17'
         distribution: 'adopt'

    - name: Make gradlew executable
      run: chmod +x ./gradlew

    - name: Build with Gradle
      run: ./gradlew build
```

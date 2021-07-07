### SCRIPTS

Compile and install the maven project:

```bash
maven compile && maven install
``` 

You can initialize the gradle setting by means of the following code snippet

```bash
gradle init
```

Update ./gradlew script to the last version with the following command 

```console 
gradle wrapper --gradle-version 6.5.1
```

Or you can update the line of gradle wrapper in the file called gradle-wrapper.properties

```bash
distributionUrl=https\://services.gradle.org/distributions/gradle-6.5.1-bin.zip
```

Principally, you can change the number of version from the aforementioned line. 


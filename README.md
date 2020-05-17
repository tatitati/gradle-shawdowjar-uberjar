
Run experiments:
```
./gradlew shadowJar
./gradlew uberJar
```
You will get with any of these:
```
/build
├── libs
│   ├── gradle-shadow-jar-with-kotlin-dsl-uber.jar
│   └── shadow.jar

```

where any of these files is like:
```
/com
    ├── github (my app)
    ├── google 
/org
    ├── checkerframework
    ├── codehaus

/META-INF

```
---
Another cool task:
```
gradle build taskTree
```
You will get:
```
:build
+--- :assemble
|    \--- :jar
|         \--- :classes
|              +--- :compileJava
|              \--- :processResources
+--- :check
|    \--- :test
|         +--- :classes
|         |    +--- :compileJava
|         |    \--- :processResources
|         \--- :testClasses
|              +--- :compileTestJava
|              |    \--- :classes
|              |         +--- :compileJava
|              |         \--- :processResources
|              \--- :processTestResources
\--- :shadowJar
     +--- :classes
     |    +--- :compileJava
     |    \--- :processResources
     \--- :somedebug
```

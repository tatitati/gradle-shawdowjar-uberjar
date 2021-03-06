
# Can I build a fat-jar?
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
# Can I see the dependencies of a task and the order in which they will run?:
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
---

# Can I publish in a local repository my artifact?:
```
 ./gradlew publish
```
You will get this publication. where *-all contains also all the dependencies 
```
/build/localrepository/
└── com
    └── mygroup
        └── gradle-shadow-jar-with-kotlin-dsl
            ├── maven-metadata.xml
            ├── maven-metadata.xml.md5
            ├── maven-metadata.xml.sha1
            └── unspecified
                ├── gradle-shadow-jar-with-kotlin-dsl-unspecified-all.jar
                ├── gradle-shadow-jar-with-kotlin-dsl-unspecified-all.jar.md5
                ├── gradle-shadow-jar-with-kotlin-dsl-unspecified-all.jar.sha1
                ├── gradle-shadow-jar-with-kotlin-dsl-unspecified.pom
                ├── gradle-shadow-jar-with-kotlin-dsl-unspecified.pom.md5
                └── gradle-shadow-jar-with-kotlin-dsl-unspecified.pom.sha1
```

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    id("com.github.johnrengelman.shadow") version "4.0.4"
    id("com.dorongold.task-tree") version "1.5"
}

repositories {
    jcenter()
}

dependencies {
    implementation("com.google.guava:guava:26.0-jre"){
        exclude(group = "com.google.code.findbugs", module = "jsr305")
    }
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("shadow")
        mergeServiceFiles()
        manifest {
            attributes(mapOf("Main-Class" to "com.github.csolem.gradle.shadow.kotlin.example.App"))
        }
    }
}

val somedebug by tasks.registering{
    doLast {
        println("im here")
    }
}


tasks.register<Jar>("uberJar") {
    archiveClassifier.set("uber")

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations
                .runtimeClasspath
                .get()
                .filter { it.name.endsWith("jar") }
                .map { zipTree(it) }
    })
}

tasks {
    build {
        dependsOn(shadowJar)
    }

    shadowJar {
        dependsOn(somedebug)
    }
}

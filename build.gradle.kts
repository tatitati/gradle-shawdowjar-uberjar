import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    id("com.github.johnrengelman.shadow") version "4.0.4"
    id("com.dorongold.task-tree") version "1.5"
    id("maven-publish")
}

group = "com.mygroup"

repositories {
    mavenCentral()
    jcenter()
    maven( "https://plugins.gradle.org/m2/")
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

publishing {
    publications {
        create<MavenPublication>("default") {
            from(components["java"])
            artifact(tasks["shadowJar"])
        }
    }

    repositories {
        maven {
            url = uri("$buildDir/localrepository")
        }
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}

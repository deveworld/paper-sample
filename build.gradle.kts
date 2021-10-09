plugins {
    kotlin("jvm") version "1.5.31"
}

group = "com.github.mcsim415"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://oss.sonatype.org/content/repositories/central")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    mavenLocal()
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation("org.mockito:mockito-core:3.12.4")
    testImplementation("org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")
}

tasks {
    processResources {
        filesMatching("**/*.yml") {
            expand(project.properties)
        }
    }

    test {
        useJUnitPlatform()
    }

    create<Jar>("paperJar") {
        from(sourceSets["main"].output)
        archiveBaseName.set(project.properties["pluginName"].toString())
        archiveVersion.set("")

        doLast {
            var dest = File(rootDir, ".debug/plugins")
            val pluginName = archiveFileName.get()
            val pluginFile = File(dest, pluginName)
            if (pluginFile.exists()) dest = File(dest, "update")

            copy {
                from(archiveFile)
                into(dest)
            }
        }
    }
}
plugins {
    java
    id("com.gradleup.shadow") version "9.3.0"
    id("xyz.jpenilla.resource-factory-bukkit-convention") version "1.3.1"
}

group = "me.bestnuts"
version = "1.0"

repositories {
    mavenCentral()
    maven(url = "https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
}

bukkitPluginYaml {
    val versionProperty = findProperty("version") as? String
        ?: throw IllegalArgumentException("version was null")

    main = "me.bestnuts.api.TargetMinimap"
    name = "TargetMinimap"
    version = versionProperty
    apiVersion = "1.21"

    description = "타겟 미니맵"

    authors = listOf("BestNuts")
}

tasks.shadowJar {
    archiveFileName.set("TargetMinimap.jar")
}
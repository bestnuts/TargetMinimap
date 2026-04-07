plugins {
    id("com.gradleup.shadow") version "9.3.0"
    id("xyz.jpenilla.resource-factory-bukkit-convention") version "1.3.1"
}

dependencies {
    compileOnly(project(":api"))
}

bukkitPluginYaml {
    val versionProperty = findProperty("version") as? String
        ?: throw IllegalArgumentException("version was null")

    main = "me.bestnuts.test.TestPlugin"
    name = "TargetMinimapTest"
    version = versionProperty
    apiVersion = "1.21"

    description = "TargetMinimap API 테스트 플러그인"

    authors = listOf("BestNuts")

    depend = listOf("TargetMinimap")
}

tasks.shadowJar {
    archiveFileName.set("TargetMinimapTest.jar")
}
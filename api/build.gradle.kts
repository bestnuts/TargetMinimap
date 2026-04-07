plugins {
    id("com.gradleup.shadow") version "9.3.0"
    id("xyz.jpenilla.resource-factory-bukkit-convention") version "1.3.1"
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
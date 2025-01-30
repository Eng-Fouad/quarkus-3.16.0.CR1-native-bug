dependencyResolutionManagement {
    versionCatalogs {
        create("deps") {
            version("dockerPluginVersion", "9.4.0") // https://plugins.gradle.org/plugin/com.bmuschko.docker-remote-api
            version("quarkusLibVersion", "3.16.0.CR1") // https://central.sonatype.com/artifact/io.quarkus/quarkus-bom

            plugin("docker", "com.bmuschko.docker-remote-api").versionRef("dockerPluginVersion")
            plugin("quarkus", "io.quarkus").versionRef("quarkusLibVersion")

            library("libs.quarkus.bom", "io.quarkus", "quarkus-bom").versionRef("quarkusLibVersion")
            library("libs.quarkus.rest.jackson", "io.quarkus", "quarkus-rest-jackson").versionRef("quarkusLibVersion")
        }
    }
}

rootProject.name = "quarkus-3.16.0.cr1-native-bug"
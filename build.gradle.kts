plugins {
    java
    alias(deps.plugins.docker)
    alias(deps.plugins.quarkus)
}

group = "io.fouad"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(deps.libs.quarkus.bom))
    implementation(deps.libs.quarkus.rest.jackson)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(23))
    }
}

val prepareDockerFiles by tasks.registering(Copy::class) {
    from(rootProject.projectDir) {
        include("src/", "gradle/", "gradlew", "settings.gradle.kts", "build.gradle.kts")
    }
    into("${project.projectDir}/build/docker")
}

tasks.register("buildNativeImage", com.bmuschko.gradle.docker.tasks.image.DockerBuildImage::class) {
    group = "fouad"
    dependsOn(prepareDockerFiles)
    dockerFile.set(file("${project.projectDir}/build/docker/src/main/docker/Dockerfile"))
    images.add("fouad/${project.name}:${project.version}")
    platform.set("linux/amd64")
    remove.set(true) // remove intermediate containers after a successful build
    finalizedBy(":saveNativeRunnerAsFile")
}

tasks.register("saveDockerImageAsFile", com.bmuschko.gradle.docker.tasks.image.DockerSaveImage::class) {
    group = "fouad"
    images.add("fouad/${project.name}:${project.version}")
    destFile.set(file("build/images/${project.name}-${project.version}.docker.image.tar"))
    useCompression.set(true)
}

val createTempContainer by tasks.registering(com.bmuschko.gradle.docker.tasks.container.DockerCreateContainer::class) {
    imageId.set("fouad/${project.name}:${project.version}")
}

val saveNativeRunnerAsFile by tasks.registering(com.bmuschko.gradle.docker.tasks.container.DockerCopyFileFromContainer::class) {
    group = "fouad"
    dependsOn(createTempContainer)
    targetContainerId(createTempContainer.get().containerId)
    remotePath.set("/code/build/${project.name}-${project.version}-runner")
    hostPath.set("${project.projectDir}/build/${project.name}-${project.version}")
    finalizedBy(":removeTempContainer")
}

tasks.register("removeTempContainer", com.bmuschko.gradle.docker.tasks.container.DockerRemoveContainer::class) {
    dependsOn(saveNativeRunnerAsFile)
    targetContainerId(saveNativeRunnerAsFile.get().containerId)
    force.set(true)
}
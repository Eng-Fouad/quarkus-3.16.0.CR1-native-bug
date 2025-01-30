# https://catalog.redhat.com/software/containers/ubi8/ubi-minimal/5c359a62bed8bd75a2c3fba8
# https://github.com/graalvm/graalvm-ce-builds/releases

# Based on RedHat OS
FROM registry.access.redhat.com/ubi8/ubi-minimal:latest

# Variables
ARG GRAALVM_VERSION=jdk-23.0.2
ARG GRAALVM_ARCH=x64
ARG GRAALVM_FILE_NAME=graalvm-community-${GRAALVM_VERSION}_linux-${GRAALVM_ARCH}_bin.tar.gz
ARG GRAALVM_URL=https://github.com/graalvm/graalvm-ce-builds/releases/download/$GRAALVM_VERSION/$GRAALVM_FILE_NAME

# Use root
USER root

# Set environemnt variables
ENV LANG=en_US.UTF-8 LANGUAGE=en_US:en LC_ALL=en_US.UTF-8
ENV JAVA_HOME=/opt/graalvm GRAALVM_HOME=/opt/graalvm
ENV PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/opt/graalvm/bin

# Install required packages for the build
RUN microdnf --setopt=install_weak_deps=0 --setopt=tsflags=nodocs install -y tar gzip gcc glibc-devel zlib-devel shadow-utils unzip gcc-c++ glibc-langpack-en fontconfig freetype-devel findutils wget && rpm -q tar gzip gcc glibc-devel zlib-devel shadow-utils unzip gcc-c++ glibc-langpack-en fontconfig freetype-devel findutils wget

# Download GraalVM JDK
RUN wget $GRAALVM_URL -O /tmp/$GRAALVM_FILE_NAME
RUN mkdir -p /opt/graalvm && tar xzf /tmp/$GRAALVM_FILE_NAME -C /opt/graalvm --strip-components=1 && rm -Rf /tmp/$GRAALVM_FILE_NAME

# Copy gradle wrapper files
COPY gradle/ /code/gradle/
COPY gradlew /code/gradlew

# Set working directory
WORKDIR /code/

# Download gradle (to cache it)
RUN /code/gradlew --no-daemon

# Copy gradle config files
COPY build.gradle.kts /code/build.gradle.kts
COPY settings.gradle.kts /code/settings.gradle.kts

# Download dependencies (to cache them)
RUN /code/gradlew dependencies --refresh-dependencies --no-daemon

# Copy source folder
COPY src/ /code/src/

# Compile the native executable file
RUN /code/gradlew build -x test --no-daemon -Dquarkus.analytics.disabled=true -Dquarkus.native.enabled=true -Dquarkus.package.jar.enabled=false

# Set the entry point
ENTRYPOINT ["/code/build/native/nativeCompile/quarkus-3.16.0.cr1-native-bug"]
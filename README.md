# [DEPRECATED]
HMKit OEM functionality can be achieved via [hmkit-crypto-telematics](https://github.com/highmobility/hmkit-crypto-java/tree/telematics) package. Released in [mavenCentral()](https://search.maven.org/artifact/com.high-mobility/hmkit-crypto-telematics/0.1/jar).
crypto-telematics is written in Java, without JNI. For that reason it is favored to hmkit-oem.

Use methods [createTelematicsContainer](https://github.com/highmobility/hmkit-crypto-java/blob/telematics/src/main/java/com/highmobility/crypto/Crypto.kt#L220) and [getPayloadFromTelematicsContainer](https://github.com/highmobility/hmkit-crypto-java/blob/telematics/src/main/java/com/highmobility/crypto/Crypto.kt#L250) instead of encrypt/decrypt in this repository.

# HMKit OEM

HMKit OEM is used to encrypt/decrypt commands that are sent to/from OEM cloud.

# Table of contents

* [Architecture](#architecture)
* [Requirements](#requirements)
* [Getting Started](#getting-started)
* [Contributing](#contributing)
* [Release](#release)
* [Licence](#Licence)

### Architecture

**General**: HMKit OEM is a Java library that handles encrypting commands between OEM and High-Mobility. Security is implemented via JNI to the HMKit Core C module.

**hmkit-oem**: Contains HMKit OEM Java classes.

**hmkit-core-jni**: Contains JNI classes to HMKit Core.

**hmkit-crypto**: Contains crypto classes and functions.

**hmkit-utils**: Contains general helper methods and classes.

### Requirements

* Linux environment. Contact High-Mobility if you require binaries for other systems.

### Getting Started

Get started with HMKit Android 📘[browse the documentation](https://high-mobility.com/learn/tutorials/for-carmakers/cloud/tutorial/).

### Contributing

Before starting please read our contribution rules 📘[Contributing](CONTRIBUTE.md)

### Setup

* `git submodule update --init --recursive`
* Build the HMKit Core: `cd hmkit-oem/src/main/jni && make && cd -`
* import the Gradle project
* Run the Tests.java tests
* If there are errors, try `Gradle clean`, `File > Invalidate caches and restart`

### Release

All of the HMKit Android packages can be released from this project. This includes hmkit-android, hmkit-core-jni, hmkit-crypto, hmkit-utils.

**Pre checks**

* Run the unit tests in Tests.java.

**Release**

* Update the "version = 1.5.0" in all of the deploy.settings files(if needed).
* Set the release environment in root build.gradle (ext property release = 0/1/2).
* Call `./gradlew artifactoryPublish` to release all of the packages.
* Call `./gradlew :hmkit-utils:artifactoryPublish` to release a specific package.
* If releasing to prod, also call `./gradlew bintrayUpload`.

If pushing the same version number, the package will be overwritten in dev, rejected in release.

### Licence
This repository is using MIT licence. See more in 📘[LICENCE](LICENCE.md)
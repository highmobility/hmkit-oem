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
* Build the HMKit Core: `./gradlew buildCore{Osx/Linux}`
* import the Gradle project
* Run the Tests.java tests
* If there are errors, try `Gradle clean`, `File > Invalidate caches and restart`

### Release

All of the HMKit OEM packages can be released from this project. This includes hmkit-oem, hmkit-core-jni, 
hmkit-crypto, hmkit-utils.

**Pre checks**

* Run the unit tests in Tests.java.

**Release**

* update ext.ver values in build.gradle or use -Pversion property
* set ext.depLocation to 1 or use -PdepLocation=1 property
* Call `./gradlew publish` to release all the packages to dev repo.
* Call `./gradlew :hmkit-utils:publish` to release a specific package.
* Call `./gradlew :hmkit-utils:publish -Prepo=gradle-release-local` to specify the repo.
* If releasing to bintray, also call `./gradlew bintrayUpload`.

If pushing the same version number, the package will be overwritten in dev, rejected in release.

For example, publish utils to dev-local and bintray, using the ext.ver value
```
./gradlew :hmkit-utils:publish -Prepo=gradle-dev-local -PdepLocation=1
./gradlew :hmkit-utils:bintrayUpload
```

### Licence
This repository is using MIT licence. See more in 📘[LICENCE](LICENCE.md)
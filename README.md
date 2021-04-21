# HMKit OEM

HMKit OEM is used to encrypt/decrypt commands that are sent to/from OEM cloud.

# Table of contents

* [Architecture](#architecture)
* [Requirements](#requirements)
* [Getting Started](#getting-started)
* [Contributing](#contributing)
* [Licence](#Licence)

### Architecture

**General**: HMKit OEM is a Java library that handles encrypting commands between OEM and High-Mobility. Security is implemented via HMKit Crypto Telematics

**hmkit-oem**: Contains HMKit OEM Java classes.

**hmkit-crypto**: Contains crypto classes and functions.

### Requirements

* Java 8

### Getting Started

Get started with HMKit OEM 📘[browse the documentation](https://high-mobility.com/learn/tutorials/for-carmakers/cloud/tutorial/).

### Contributing

Before starting please read our contribution rules 📘[Contributing](CONTRIBUTE.md)

### Setup

* `git submodule update --init --recursive`
* import the Gradle project
* `./gradlew test`

### Licence
This repository is using MIT licence. See more in 📘[LICENCE](LICENCE.md)

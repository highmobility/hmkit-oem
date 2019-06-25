# HMKit OEM

This repository is used to Encrypt/Decrypt commands that are sent from/to OEM cloud.

### Dependencies

hmkit-utils, hmkit-crypto, hmkit-core-jni

### Install

Releases are pushed to jcenter. To include hmkit-oem in your project, add to build.gradle:

```
repositories {
  jcenter()
}

dependencies {
  implementation('com.highmobility:hmkit-oem:1.2.0')
}
```

Find the latest version name in https://bintray.com/high-mobility/maven/hmkit-oem

### Environment

HMKit OEM requires our core binary and currently includes one that is built for Linux in the /lib folder.
This means the project can only be built in Linux. Contact High-Mobility if you require binaries for other
systems.

### How to encrypt/decrypt commands?

This library is meant to be used in conjunction with hmkit-crypto. The latter is used to create key-pairs, certificates and sign the data.
Use the crypto lib to create key pairs and Access certificates for your vehicles. They will be used in the command's encryption/decryption.

The main access point for this library is the Telematics class. Here you find methods

```java
public Bytes decryptCommand(PrivateKey privateKey, AccessCertificate certificate, Bytes command) throws CryptoException
```

Use this to decrypt a command that was received from High-Mobility.

And

```java
public Bytes encryptCommand(PrivateKey privateKey, AccessCertificate certificate, Bytes nonce,
                                DeviceSerial serial, Bytes command) throws CryptoException
```

Use this to encrypt a command that will be sent to High-Mobility.

### Tutorial

There is a tutorial about the general flow OEM-s can follow to implement our SDK:

https://high-mobility.com/learn/tutorials/for-carmakers/cloud/tutorial/


### 
### Setup

* git submodule update --init --recursive
* import the Gradle project.
* Build the core: `cd src/main/jni && make && cd -`
* Run the TestSignatures.java tests.
* If there are errors: Try `Gradle clean`, `File > Invalidate caches and restart`.


### Building the core
Core is not included in the repository and needs to be built on first clone
```
cd src/main/jni && make && cd -
```

### Release

#### Pre checks

* run the unit-tests

#### Release

// TODO: make a test release

This project bundles all of the OEM SDK packages: hmkit-oem, hmkit-crypto and hmkit-utils.

For a release, update the "version = 1.5.0" in all of the deploy.settings files(if needed).

call ./gradlew artifactoryPublish to release all of the packages.
call ./gradlew :hmkit-oem:artifactoryPublish to release a specific package.

If pushing the same version number, in dev package will be overwritten, in release rejected.

If releasing to prod, also call "./gradlew bintrayUpload".
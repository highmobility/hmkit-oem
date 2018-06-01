# HMKit OEM

This repository is used to Encrypt/Decrypt commands that are sent from/to OEM cloud.

### Dependencies

hmkit-utils, hmkit-crypto

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
public byte[] decryptCommand(PrivateKey privateKey, AccessCertificate certificate, Bytes command) throws CryptoException
```

Use this to decrypt a command that was received from High-Mobility.

And

```java
public byte[] encryptCommand(PrivateKey privateKey, AccessCertificate certificate, Bytes nonce,
                                DeviceSerial serial, Bytes command) throws CryptoException
```

Use this to encrypt a command that will be sent to High-Mobility.

Here is some pseudo code on how to encrypt/decrypt commands:

https://gist.github.com/ttiganik/5064c746aee8d41efc093ce248e74a2e

This is how you would create a keypair and and serial number with Crypto:

```scala
    val pair = Crypto.createKeypair()
    val serialBytes = Crypto.createSerialNumber()

    val serialNumber = ByteUtils.hexFromBytes(serialBytes)
    val privateKey = pair.getPrivateKeyBase64()
    val publicKey = pair.getPublicKeyBase64()
```

# What is this repository for? #

This repository is used to Encrypt/Decrypt commands that are sent from/to OEM cloud.

# Environment #

HMKit OEM requires our core binary and currently one built for Linux is included in the /lib folder.
This means the project can only be built in Linux. Contact High-Mobility if you require binaries for other
systems.

# How to encrypt/decrypt commands? #

This library is meant to be used in conjunction with hmkit-crypto. The latter is used to create key-pairs, certificates and sign the data.
Use the crypto lib to create key pairs and Access certificates for your vehicles. They will be used in the command's encryption/decryption.

The main access point for this library is the Telematics class. Here you find methods

```java
public byte[] decryptCommand(byte[] privateKey, AccessCertificate certificate, byte[] command) throws CryptoException
```

Use this to decrypt a command that was received from High-Mobility.

And

```java
public byte[] encryptCommand(byte[] privateKey, AccessCertificate certificate, byte[] nonce,
                                byte[] serial, byte[] command) throws CryptoException
```

Use this to encrypt a command that will be sent to High-Mobility.

Here is some pseudo code on how to encrypt/decrypt commands:

https://bitbucket.org/highmobility/oem-backend-app/src/896b4a20ad20b58a3d3472c333fa46eacac87333/src/main/scala/com/hm/oem/hmcore/CoreTest.scala?at=master&fileviewer=file-view-default

This is how you would create a keypair and and serial number with Crypto:

```scala
    val pair = Crypto.createKeypair()
    val serialBytes = Crypto.createSerialNumber()

    val serialNumber = Bytes.hexFromBytes(serialBytes)
    val privateKey = pair.getPrivateKeyBase64()
    val publicKey = pair.getPublicKeyBase64()
```

# Dependencies #

hmkit-utils, hmit-crypto
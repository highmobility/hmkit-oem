# What is this repository for? #

This repository is used to Encrypt/Decrypt commands that are sent from/to OEM cloud. 

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




# Dependencies #

hmkit-utils, hmit-crypto
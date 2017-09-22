package com.highmobility.crypto;


import com.highmobility.utils.Base64;

/**
 * Created by ttiganik on 26/05/16.
 */
public class KeyPair {
    byte[] privateKey;
    byte[] publicKey;

    public KeyPair(byte[] privateKey, byte[] publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public String getPublicKeyBase64() {
        return Base64.encode(publicKey);
    }

    public byte[] getPrivateKey() {
        return privateKey;
    }

    public String getPrivateKeyBase64() {
        return Base64.encode(privateKey);
    }
}

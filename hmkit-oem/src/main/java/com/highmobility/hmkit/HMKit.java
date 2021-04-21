/*
 * The MIT License
 *
 * Copyright (c) 2014- High-Mobility GmbH (https://high-mobility.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.highmobility.hmkit;

import com.highmobility.crypto.AccessCertificate;
import com.highmobility.crypto.Crypto;
import com.highmobility.crypto.value.DeviceSerial;
import com.highmobility.crypto.value.PrivateKey;
import com.highmobility.value.Bytes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HMKit {
    private static volatile HMKit instance;

    public static Logger logger = LoggerFactory.getLogger(HMKit.class);

    private Crypto crypto;

    /**
     * @return The crypto.
     */
    public Crypto getCrypto() {
        return crypto;
    }

    /**
     * @return The instance of the HMKit.
     */
    public static HMKit getInstance() {
        if (instance == null) {
            synchronized (HMKit.class) {
                if (instance == null) instance = new HMKit();
            }
        }

        return instance;
    }

    private HMKit() {
        // protect against reflection where private is not respected.
        if (instance != null) {
            throw new RuntimeException("Use getInstance() to get the HMKit singleton");
        }

        crypto = new Crypto();
    }

    /**
     * Decrypt an incoming command.
     *
     * @param privateKey  the vehicle's private key
     * @param certificate the vehicle's Access Certificate
     * @param command     the command that will be decrypted
     * @return the decrypted command
     */
    public Bytes decrypt(PrivateKey privateKey, AccessCertificate certificate,
                         Bytes command) {
        return crypto.getPayloadFromTelematicsContainer(command, privateKey, certificate);
    }

    /**
     * Encrypt a command so that it can be sent to the HM server.
     *
     * @param privateKey  the vehicle's private key
     * @param certificate the vehicle's Access Certificate
     * @param nonce       the nonce
     * @param serial      the vehicle's serial number
     * @param command     the command that will be encrypted
     * @return the encrypted command
     */
    public Bytes encrypt(PrivateKey privateKey, AccessCertificate certificate,
                         Bytes nonce,
                         DeviceSerial serial, Bytes command) {
        return encryptCommand(ContentType.AUTO_API, privateKey, certificate, nonce, serial, command);
    }

    /**
     * Encrypt a command so that it can be sent to the HM server.
     *
     * @param contentType the content type. See {@link ContentType} for possible types.
     * @param privateKey  the vehicle's private key
     * @param certificate the vehicle's Access Certificate
     * @param nonce       the nonce
     * @param serial      the vehicle's serial number
     * @param command     the command that will be encrypted
     * @return the encrypted command
     */
    public Bytes encrypt(ContentType contentType, PrivateKey privateKey, AccessCertificate certificate,
                         Bytes nonce, DeviceSerial serial, Bytes command) {

        return crypto.createTelematicsContainer(command, privateKey, serial, certificate, nonce);
    }

    /**
     * Decrypt an incoming command.
     *
     * @param privateKey  the vehicle's private key
     * @param certificate the vehicle's Access Certificate
     * @param command     the command that will be decrypted
     * @return the decrypted command
     * @deprecated use {@link #decrypt(PrivateKey, AccessCertificate, Bytes)} instead
     */
    @Deprecated
    public static Bytes decryptCommand(PrivateKey privateKey, AccessCertificate certificate,
                                       Bytes command) {

        return getInstance().decrypt(privateKey, certificate, command);
    }

    /**
     * Encrypt a command so that it can be sent to the HM server.
     *
     * @param privateKey  the vehicle's private key
     * @param certificate the vehicle's Access Certificate
     * @param nonce       the nonce
     * @param serial      the vehicle's serial number
     * @param command     the command that will be encrypted
     * @return the encrypted command
     * @deprecated use {@link #encrypt(PrivateKey, AccessCertificate, Bytes, DeviceSerial, Bytes)}
     * instead
     */
    @Deprecated
    public static Bytes encryptCommand(PrivateKey privateKey, AccessCertificate certificate,
                                       Bytes nonce,
                                       DeviceSerial serial, Bytes command) {
        return getInstance().encrypt(ContentType.AUTO_API, privateKey, certificate, nonce, serial, command);
    }

    /**
     * Encrypt a command so that it can be sent to the HM server.
     *
     * @param contentType the content type. See {@link ContentType} for possible types.
     * @param privateKey  the vehicle's private key
     * @param certificate the vehicle's Access Certificate
     * @param nonce       the nonce
     * @param serial      the vehicle's serial number
     * @param command     the command that will be encrypted
     * @return the encrypted command
     * @deprecated use {@link #encrypt(ContentType, PrivateKey, AccessCertificate, Bytes, DeviceSerial, Bytes)}
     * instead
     */
    @Deprecated
    public static Bytes encryptCommand(ContentType contentType, PrivateKey privateKey, AccessCertificate certificate,
                                       Bytes nonce, DeviceSerial serial, Bytes command) {
        return getInstance().encrypt(contentType, privateKey, certificate, nonce, serial, command);
    }
}

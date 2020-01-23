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

import com.highmobility.btcore.HMBTCore;
import com.highmobility.btcore.HMBTCoreInterface;
import com.highmobility.crypto.AccessCertificate;
import com.highmobility.crypto.Crypto;
import com.highmobility.crypto.value.DeviceSerial;
import com.highmobility.crypto.value.PrivateKey;
import com.highmobility.value.Bytes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HMKit {

    private static final String invalidArgumentExceptionMessage = "Invalid argument";

    private static volatile HMKit instance;

    public static Logger logger = LoggerFactory.getLogger(HMKit.class);

    private Crypto crypto;

    /**
     * @return The crypto.
     */
    public Crypto getCrypto() {
        return crypto;
    }

    static {
        // load the core in static since it is used in static methods.
        Class testClass = HMKit.class;
        boolean jar =
                testClass.getResource(testClass.getSimpleName() + ".class").toString().startsWith("jar");

        if (jar) {
            try {
                NativeUtils.loadLibraryFromJar("/libhmbtcore.jnilib");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // this loads the lib locally for unit tests
            Path resourceDirectory = Paths.get("");
            String abs = resourceDirectory.toAbsolutePath() + "/../lib/libhmbtcore.jnilib";
            System.load(abs);
        }
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

        crypto = new Crypto(new HMBTCore());
    }

    /**
     * Decrypt an incoming command.
     *
     * @param privateKey  the vehicle's private key
     * @param certificate the vehicle's Access Certificate
     * @param command     the command that will be decrypted
     * @return the decrypted command
     * @throws CryptoException When arguments are invalid or the decryption failed
     */
    public static Bytes decryptCommand(PrivateKey privateKey, AccessCertificate certificate,
                                       Bytes command) throws CryptoException {

        validatePrivateKey(privateKey);
        validateCertificate(certificate);

        if (command == null) {
            throw new CryptoException(CryptoException.Type.INVALID_ARGUMENT,
                    invalidArgumentExceptionMessage);
        }

        HMBTCoreInterfaceImpl container = new HMBTCoreInterfaceImpl(privateKey.getByteArray(),
                certificate);
        HMBTCore coreJni = initCore(container);

        coreJni.HMBTCoreTelematicsReceiveData(container, command.getLength(), command
                .getByteArray());

        validateResult(container, "Decryption failed. Check the parameters");
        return container.getResponse();
    }

    /**
     * Encrypt a command so that it can be sent to the HM server. The encrypted command will be
     * returned in CoreCallback's didEncryptCommand.
     *
     * @param privateKey  the vehicle's private key
     * @param certificate the vehicle's Access Certificate
     * @param nonce       the nonce
     * @param serial      the vehicle's serial number
     * @param command     the command that will be encrypted
     * @return the encrypted command
     * @throws CryptoException When arguments are invalid or the decryption failed
     */
    public static Bytes encryptCommand(PrivateKey privateKey, AccessCertificate certificate,
                                       Bytes nonce,
                                       DeviceSerial serial, Bytes command) throws CryptoException {
        return encryptCommand(ContentType.AUTO_API, privateKey, certificate, nonce, serial, command);
    }

    /**
     * Encrypt a command so that it can be sent to the HM server. The encrypted command will be
     * returned in CoreCallback's didEncryptCommand.
     *
     * @param contentType the content type. See {@link ContentType} for possible types.
     * @param privateKey  the vehicle's private key
     * @param certificate the vehicle's Access Certificate
     * @param nonce       the nonce
     * @param serial      the vehicle's serial number
     * @param command     the command that will be encrypted
     * @return the encrypted command
     * @throws CryptoException When arguments are invalid or the decryption failed
     */
    public static Bytes encryptCommand(ContentType contentType, PrivateKey privateKey, AccessCertificate certificate,
                                         Bytes nonce, DeviceSerial serial, Bytes command) throws CryptoException {
        validatePrivateKey(privateKey);
        validateCertificate(certificate);

        if (command == null) {
            throw new CryptoException(CryptoException.Type.INVALID_ARGUMENT,
                    invalidArgumentExceptionMessage);
        }

        if (nonce == null || nonce.getLength() != 9) {
            throw new CryptoException(CryptoException.Type.INVALID_ARGUMENT,
                    invalidArgumentExceptionMessage);
        }

        if (serial == null) {
            throw new CryptoException(CryptoException.Type.INVALID_ARGUMENT,
                    invalidArgumentExceptionMessage);
        }

        HMBTCoreInterfaceImpl container = new HMBTCoreInterfaceImpl(serial.getByteArray(),
                privateKey.getByteArray(), certificate);
        HMBTCore coreJni = initCore(container);

        coreJni.HMBTCoreSendTelematicsCommand(container, serial.getByteArray(), nonce
                .getByteArray(), contentType.asInt(), command.getLength(), command.getByteArray());

        validateResult(container, "Encryption failed. Check the parameters");
        return container.getResponse();
    }

    private static HMBTCore initCore(HMBTCoreInterface container) {
        HMBTCore jniToCore = new HMBTCore();
        jniToCore.HMBTCoreInit(container);
        return jniToCore;
    }

    private static void validateCertificate(AccessCertificate certificate) throws CryptoException {
        if (certificate == null) {
            throw new CryptoException(CryptoException.Type.INVALID_ARGUMENT,
                    invalidArgumentExceptionMessage);
        }
    }

    private static void validatePrivateKey(PrivateKey privateKey) throws CryptoException {
        if (privateKey == null) {
            throw new CryptoException(CryptoException.Type.INVALID_ARGUMENT,
                    invalidArgumentExceptionMessage);
        }
    }

    private static void validateResult(HMBTCoreInterfaceImpl container, String message) throws
            CryptoException {
        if (container.getResponse() == null) {
            throw new CryptoException(CryptoException.Type.INTERNAL_ERROR, message);
        }
    }
}

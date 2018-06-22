/*
 * HMKit OEM - HMKit for OEM
 * Copyright (C) 2018 High-Mobility <licensing@high-mobility.com>
 *
 * This file is part of HMKit OEM.
 *
 * HMKit OEM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * HMKit OEM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with HMKit OEM.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.highmobility.hmkit;

import com.highmobility.btcore.HMBTCore;
import com.highmobility.btcore.HMBTCoreInterface;
import com.highmobility.crypto.AccessCertificate;
import com.highmobility.crypto.value.DeviceSerial;
import com.highmobility.crypto.value.PrivateKey;
import com.highmobility.value.Bytes;

public class Telematics {

    private static final String invalidArgumentExceptionMessage = "Invalid argument";

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
                                       Bytes command)
            throws CryptoException {

        validatePrivateKey(privateKey);
        validateCertificate(certificate);

        if (command == null) {
            throw new CryptoException(CryptoException.Type.INVALID_ARGUMENT,
                    invalidArgumentExceptionMessage);
        }

        HMBTCoreInterface container = new HMBTCoreInterfaceImpl(privateKey.getByteArray(),
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

        HMBTCoreInterface container = new HMBTCoreInterfaceImpl(serial.getByteArray(), privateKey
                .getByteArray(), certificate);
        HMBTCore coreJni = initCore(container);

        coreJni.HMBTCoreSendTelematicsCommand(container, serial.getByteArray(), nonce
                .getByteArray(), command.getLength(), command.getByteArray());

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

    private static void validateResult(HMBTCoreInterface container, String message) throws
            CryptoException {
        if (container.getResponse() == null) {
            throw new CryptoException(CryptoException.Type.INTERNAL_ERROR, message);
        }
    }
}

package com.highmobility.core;

import com.highmobility.btcore.HMBTCore;
import com.highmobility.btcore.HMBTCoreInterface;
import com.highmobility.btcore.HMDevice;
import com.highmobility.crypto.AccessCertificate;

public class Core implements HMBTCoreInterface {
    static String invalidArgumentExceptionMessage = "Invalid argument";

    HMBTCore core = new HMBTCore();
    byte[] serial;
    byte[] privateKey;
    AccessCertificate certificate;
    byte[] response;

    public Core() {
        core.HMBTCoreInit(this);
    }

    /**
     * Decrypt an incoming command.
     *
     * @param privateKey the vehicle's private key
     * @param certificate the vehicle's Access Certificate
     * @param command the command that will be decrypted
     *
     * @return the decrypted command
     */
    public byte[] decryptCommand(byte[] privateKey, AccessCertificate certificate, byte[] command)
            throws CryptoException {

        if (privateKey.length == 0 || privateKey.length != 9) {
            throw new CryptoException(CryptoException.Type.INVALID_ARGUMENT, invalidArgumentExceptionMessage);
        }

        if (certificate == null) {
            throw new CryptoException(CryptoException.Type.INVALID_ARGUMENT, invalidArgumentExceptionMessage);
        }

        if (command == null || command.length == 0) {
            throw new CryptoException(CryptoException.Type.INVALID_ARGUMENT, invalidArgumentExceptionMessage);
        }

        response = null;

        core.HMBTCoreTelematicsReceiveData(this, command.length, command);

        if (response == null) {
            throw new CryptoException(CryptoException.Type.INTERNAL_ERROR,
                    "Decryption failed. Check the parameters");
        }

        return response;
    }

    /**
     * Encrypt a command so that it can be sent to the HM server. The encrypted command will be
     * returned in CoreCallback's didEncryptCommand.
     *
     * @param privateKey the vehicle's private key
     * @param certificate the vehicle's Access Certificate
     * @param nonce the nonce
     * @param serial the vehicle's serial number
     * @param command the command that will be encrypted
     *
     * @return the encrypted command
     */
    public byte[] encryptCommand(byte[] privateKey, AccessCertificate certificate, byte[] nonce,
                                 byte[] serial, byte[] command) throws CryptoException {
        if (privateKey.length == 0 || privateKey.length != 9) {
            throw new CryptoException(CryptoException.Type.INVALID_ARGUMENT, invalidArgumentExceptionMessage);
        }

        if (certificate == null) {
            throw new CryptoException(CryptoException.Type.INVALID_ARGUMENT, invalidArgumentExceptionMessage);
        }

        if (command == null || command.length == 0) {
            throw new CryptoException(CryptoException.Type.INVALID_ARGUMENT, invalidArgumentExceptionMessage);
        }

        if (nonce == null || nonce.length != 9) {
            throw new CryptoException(CryptoException.Type.INVALID_ARGUMENT, invalidArgumentExceptionMessage);
        }

        if (serial == null || serial.length != 9) {
            throw new CryptoException(CryptoException.Type.INVALID_ARGUMENT, invalidArgumentExceptionMessage);
        }

        response = null;

        core.HMBTCoreSendTelematicsCommand(this, serial, nonce, command.length, command);

        if (response == null) {
            throw new CryptoException(CryptoException.Type.INTERNAL_ERROR,
                    "Encryption failed. Check the parameters");
        }

        return response;
    }

    @Override
    public void HMApiCallbackTelematicsCommandIncoming(HMDevice device, int id, int length,
                                                       byte[] data) {
        if (id == 0x02) {
            // failed
        }
        else {
            response = trimmedBytes(data, length);
        }
    }

    @Override
    public int HMBTHalTelematicsSendData(byte[] issuer, byte[] serial, int length, byte[] data) {
        response = trimmedBytes(data, length);
        return 0;
    }

    @Override
    public int HMPersistenceHalgetPublicKey(byte[] serial, byte[] publicKey, byte[] startDate,
                                            byte[] endDate, int[] commandSize, byte[] command) {
        copyBytes(certificate.getGainerPublicKey(), publicKey);
        copyBytes(certificate.getStartDateBytes(), startDate);
        copyBytes(certificate.getEndDateBytes(), endDate);
        byte[] permissions = certificate.getPermissions();

        copyBytes(permissions, command);
        commandSize[0] = permissions.length;

        return 0;
    }

    @Override public int HMPersistenceHalgetLocalPrivateKey(byte[] privateKey) {
        copyBytes(this.privateKey, privateKey);
        return 0;
    }

    @Override public int HMPersistenceHalgetSerial(byte[] serial) {
        copyBytes(this.serial, serial);
        return 0;
    }

    @Override public int HMBTHalInit() {
        return 0;
    }

    @Override public int HMBTHalScanStart() {
        return 0;
    }

    @Override public int HMBTHalScanStop() {
        return 0;
    }

    @Override public int HMBTHalAdvertisementStart(byte[] issuer, byte[] appID) {
        return 0;
    }

    @Override public int HMBTHalAdvertisementStop() {
        return 0;
    }

    @Override public int HMBTHalConnect(byte[] mac) {
        return 0;
    }

    @Override public int HMBTHalDisconnect(byte[] mac) {
        return 0;
    }

    @Override public int HMBTHalServiceDiscovery(byte[] mac) {
        return 0;
    }

    @Override
    public int HMBTHalWriteData(byte[] mac, int length, byte[] data, int characteristic) {
        return 0;
    }

    @Override public int HMBTHalReadData(byte[] mac, int offset, int characteristic) {
        return 0;
    }

    @Override public int HMPersistenceHalgetLocalPublicKey(byte[] publicKey) {
        return 0;
    }

    @Override public int HMPersistenceHalgetDeviceCertificate(byte[] cert) {
        return 0;
    }

    @Override public int HMPersistenceHalgetCaPublicKey(byte[] cert) {
        return 0;
    }

    @Override public int HMPersistenceHalgetOEMCaPublicKey(byte[] cert) {
        return 0;
    }

    @Override
    public int HMPersistenceHaladdPublicKey(byte[] serial, byte[] publicKey, byte[] startDate, byte[] endDate, int commandSize, byte[] command) {
        return 0;
    }

    @Override
    public int HMPersistenceHalgetPublicKeyByIndex(int index, byte[] serial, byte[] publicKey, byte[] startDate, byte[] endDate, int[] commandSize, byte[] command) {
        return 0;
    }

    @Override public int HMPersistenceHalgetPublicKeyCount(int[] count) {
        return 0;
    }

    @Override public int HMPersistenceHalremovePublicKey(byte[] serial) {
        return 0;
    }

    @Override public int HMPersistenceHaladdStoredCertificate(byte[] cert, int size) {
        return 0;
    }

    @Override
    public int HMPersistenceHalgetStoredCertificate(byte[] serial, byte[] cert, int[] size) {
        return 0;
    }

    @Override public int HMPersistenceHaleraseStoredCertificate(byte[] serial) {
        return 0;
    }

    @Override public void HMApiCallbackEnteredProximity(HMDevice device) {

    }

    @Override public void HMApiCallbackExitedProximity(HMDevice device) {

    }

    @Override
    public void HMApiCallbackCustomCommandIncoming(HMDevice device, byte[] data, int length) {

    }

    @Override
    public void HMApiCallbackCustomCommandResponse(HMDevice device, byte[] data, int length) {

    }

    @Override
    public int HMApiCallbackGetDeviceCertificateFailed(HMDevice device, byte[] nonce) {
        return 0;
    }

    @Override public int HMApiCallbackPairingRequested(HMDevice device) {
        return 0;
    }

    @Override public void HMCryptoHalGenerateNonce(byte[] nonce) {

    }

    void copyBytes(byte[] from, byte[] to) {
        for (int i = 0; i < from.length; i++) {
            to[i] = from[i];
        }
    }

    byte[] trimmedBytes(byte[] bytes, int length) {
        if (bytes.length == length) return bytes;

        byte[] trimmedBytes = new byte[length];

        for (int i = 0; i < length; i++) {
            trimmedBytes[i] = bytes[i];
        }

        return trimmedBytes;
    }
}

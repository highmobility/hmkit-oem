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

import com.highmobility.btcore.HMBTCoreInterface;
import com.highmobility.btcore.HMDevice;
import com.highmobility.crypto.AccessCertificate;
import com.highmobility.value.Bytes;

public class HMBTCoreInterfaceImpl implements HMBTCoreInterface {

    private byte[] serial;
    private byte[] privateKey;
    private AccessCertificate certificate;
    private byte[] response = null;

    public HMBTCoreInterfaceImpl() {
    }

    HMBTCoreInterfaceImpl(byte[] privateKey, AccessCertificate certificate) {
        this.privateKey = privateKey;
        this.certificate = certificate;
    }

    HMBTCoreInterfaceImpl(byte[] serial, byte[] privateKey, AccessCertificate certificate) {
        this.serial = serial;
        this.privateKey = privateKey;
        this.certificate = certificate;
    }

    public byte[] getSerial() {
        return serial;
    }

    public void setSerial(byte[] serial) {
        this.serial = serial;
    }

    public byte[] getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }

    public AccessCertificate getCertificate() {
        return certificate;
    }

    public void setCertificate(AccessCertificate certificate) {
        this.certificate = certificate;
    }

    public Bytes getResponse() {
        return new Bytes(response);
    }

    public void setResponse(byte[] response) {
        this.response = response;
    }

    @Override
    public int HMBTHalTelematicsSendData(byte[] issuer, byte[] serial, int length, byte[] data) {
        response = trimmedBytes(data, length);
        return 0;
    }

    @Override
    public int HMPersistenceHalgetPublicKey(byte[] serial, byte[] cert, int[] size) {
        copyBytes(certificate.getByteArray(), cert);
        size[0] = certificate.getLength();
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

    @Override public int HMBTHalLog(int logLevel, byte[] string) {
        HMKit.logger.debug(new String(string));
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
    public int HMPersistenceHaladdPublicKey(byte[] serial, byte[] cert, int size) {
        return 0;
    }

    @Override
    public int HMPersistenceHalgetPublicKeyByIndex(int index, byte[] cert, int[] size) {
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
    public void HMApiCallbackCustomCommandIncoming(HMDevice device, int contentType, byte[] data,
                                                   int length) {

    }

    @Override
    public void HMApiCallbackCustomCommandResponse(HMDevice device, int contentType, byte[] data,
                                                   int length) {

    }

    @Override public void HMApiCallbackCustomCommandResponseError(HMDevice device, int errorType) {

    }

    @Override
    public int HMApiCallbackGetDeviceCertificateFailed(HMDevice device, byte[] nonce) {
        return 0;
    }

    @Override public int HMApiCallbackPairingRequested(HMDevice device) {
        return 0;
    }

    @Override
    public void HMApiCallbackTelematicsCommandIncoming(HMDevice device, int id, int contentType, int length, byte[] data) {
        if (id == 0x02) {
            // failed
        } else {
            response = trimmedBytes(data, length);
        }
    }

    @Override public void HMCryptoHalGenerateNonce(byte[] nonce) {

    }

    @Override
    public void HMApiCallbackRevokeResponse(HMDevice device, byte[] data, int length, int status) {

    }

    @Override public void HMApiCallbackRevokeIncoming(HMDevice device) {

    }

    @Override
    public void HMApiCallbackErrorCommandIncoming(HMDevice device, int error, int errorType) {

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

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

import com.highmobility.btcore.HMBTCoreInterface;
import com.highmobility.btcore.HMDevice;
import com.highmobility.crypto.AccessCertificate;

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

    @Override
    public byte[] getSerial() {
        return serial;
    }

    @Override
    public void setSerial(byte[] serial) {
        this.serial = serial;
    }

    @Override
    public byte[] getPrivateKey() {
        return privateKey;
    }

    @Override
    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public AccessCertificate getCertificate() {
        return certificate;
    }

    @Override
    public void setCertificate(AccessCertificate certificate) {
        this.certificate = certificate;
    }

    @Override
    public byte[] getResponse() {
        return response;
    }

    @Override
    public void setResponse(byte[] response) {
        this.response = response;
    }

    @Override
    public void HMApiCallbackTelematicsCommandIncoming(HMDevice device, int id, int length,
                                                       byte[] data) {
        if (id == 0x02) {
            // failed
        } else {
            response = trimmedBytes(data, length);
        }
    }

    @Override
    public int HMBTHalTelematicsSendData(byte[] issuer, byte[] serial, int length, byte[] data) {
        response = trimmedBytes(data, length);
        return 0;
    }

    @Override
    public int HMPersistenceHalgetPublicKey(byte[] serial, byte[] cert, int[] size) {
        copyBytes(certificate.getBytes(), cert);
        size[0] = certificate.getBytes().length;
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

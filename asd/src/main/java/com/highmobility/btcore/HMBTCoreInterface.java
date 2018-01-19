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

package com.highmobility.btcore;

import com.highmobility.crypto.AccessCertificate;

public interface HMBTCoreInterface {

    //Characteristics id's
    public static final int hm_characteristic_link_read     =   0x02;
    public static final int hm_characteristic_link_write    =   0x03;
    public static final int hm_characteristic_alive         =   0x04;
    public static final int hm_characteristic_info          =   0x05;
    public static final int hm_characteristic_sensing_read  =   0x06;
    public static final int hm_characteristic_sensing_write =   0x07;

    //BT HAL

    //Initialize central or peripheral
    //TT
    int HMBTHalInit();

    //Start stop central scanning
    int HMBTHalScanStart();
    int HMBTHalScanStop();

    //Start stop peripheral advertisement
    //TT
    int HMBTHalAdvertisementStart(byte[] issuer, byte[] appID);
    int HMBTHalAdvertisementStop();

    //Connect disconnect to peripheral
    int HMBTHalConnect(byte[] mac);
    int HMBTHalDisconnect(byte[] mac);

    //Start peripheral service discovery
    int HMBTHalServiceDiscovery(byte[] mac);

    //Write data to peripheral or central
    //TT
    int HMBTHalWriteData(byte[] mac, int length, byte[] data, int characteristic);
    //Read data from peripheral
    int HMBTHalReadData(byte[] mac, int offset, int characteristic);

    int HMBTHalTelematicsSendData(byte[] issuer, byte[] serial, int length, byte[] data);

    //PERSISTENCE HAL

    //Get current device serial number
    //TT
    int HMPersistenceHalgetSerial(byte[] serial);
    //Get current device public key
    //TT
    int HMPersistenceHalgetLocalPublicKey(byte[] publicKey);
    //Get current device public key
    //TT
    int HMPersistenceHalgetLocalPrivateKey(byte[] privateKey);
    //TT
    int HMPersistenceHalgetDeviceCertificate(byte[] cert);

    int HMPersistenceHalgetCaPublicKey(byte[] cert);

    int HMPersistenceHalgetOEMCaPublicKey(byte[] cert);

    //Add remote device public key to storage
    //TT
    int HMPersistenceHaladdPublicKey(byte[] serial, byte[] cert, int size);
	//Get remote device publick key from storage
	//TT
	int HMPersistenceHalgetPublicKey(byte[] serial, byte[] cert, int[] size);
	//TT
	int HMPersistenceHalgetPublicKeyByIndex(int index, byte[] cert, int[] size);
	//TT
    int HMPersistenceHalgetPublicKeyCount(int[] count);
    //Remove remote device public key from storage
    //TT
    int HMPersistenceHalremovePublicKey(byte[] serial);

    //Add certificate to storage
    //TT
    int HMPersistenceHaladdStoredCertificate(byte[] cert, int size);
    //Get certificate from storage
    //TT
    int HMPersistenceHalgetStoredCertificate(byte[] serial, byte[] cert, int[] size);
    //Delete certificate from storage
    //TT
    int HMPersistenceHaleraseStoredCertificate(byte[] serial);


    //Proximity
    //TT
    void HMApiCallbackEnteredProximity(HMDevice device);
    //TT
    void HMApiCallbackExitedProximity(HMDevice device);

    //Callback
    //TT
    void HMApiCallbackCustomCommandIncoming(HMDevice device, byte[] data, int length); // received custom command
    void HMApiCallbackCustomCommandResponse(HMDevice device, byte[] data, int length);
    int HMApiCallbackGetDeviceCertificateFailed(HMDevice device, byte[] nonce); // ret false means don't continue
    int HMApiCallbackPairingRequested(HMDevice device); //ret false means don't continue

    void HMApiCallbackTelematicsCommandIncoming(HMDevice device, int id, int length, byte[] data);

    //crypto
    void HMCryptoHalGenerateNonce(byte[] nonce);


    // other
    byte[] getSerial();

    void setSerial(byte[] serial);

    byte[] getPrivateKey();

    void setPrivateKey(byte[] privateKey);

    AccessCertificate getCertificate();

    void setCertificate(AccessCertificate certificate);

    byte[] getResponse();

    void setResponse(byte[] response);
}

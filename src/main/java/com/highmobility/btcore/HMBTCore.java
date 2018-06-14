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

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by ttiganik on 13/04/16.
 */
public class HMBTCore {
    static {
        boolean jar = HMBTCore.class.getResource("HMBTCore.class").toString().startsWith("jar");
        if (jar) {
            try {
                NativeUtils.loadLibraryFromJar("/libhmbtcore.jnilib");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // This is only used by junit and it only works in linux.
            Path resourceDirectory = Paths.get("");
            String abs = resourceDirectory.toAbsolutePath().getParent().getParent() + "/lib/libhmbtcore.jnilib";
            System.load(abs);
        }
    }

    //Init core
    //interface is class reference what implements HMBTCoreInterface
    //TT
    public native void HMBTCoreInit(HMBTCoreInterface coreInterface);

    //Send clock beat to core
    public native void HMBTCoreClock(HMBTCoreInterface coreInterface);

    //CORE SENSING

    public native void HMBTCoreSensingReadNotification(HMBTCoreInterface coreInterface, byte[]
            mac, int characteristic);

    public native void HMBTCoreSensingReadResponse(HMBTCoreInterface coreInterface, byte[] data,
                                                   int size, int offset, byte[] mac, int
                                                           characteristic);

    public native void HMBTCoreSensingWriteResponse(HMBTCoreInterface coreInterface, byte[] mac,
                                                    int characteristic);

    public native void HMBTCoreSensingPingNotification(HMBTCoreInterface coreInterface, byte[]
            mac, int characteristic);

    public native void HMBTCoreSensingProcessAdvertisement(HMBTCoreInterface coreInterface,
                                                           byte[] mac, byte[] data, int size);

    public native void HMBTCoreSensingDiscoveryEvent(HMBTCoreInterface coreInterface, byte[] mac);

    public native void HMBTCoreSensingScanStart(HMBTCoreInterface coreInterface);

    public native void HMBTCoreSensingConnect(HMBTCoreInterface coreInterface, byte[] mac);

    public native void HMBTCoreSensingDisconnect(HMBTCoreInterface coreInterface, byte[] mac);

    //CORE LINK

    //Initialize link object in core
    //TT
    public native void HMBTCorelinkConnect(HMBTCoreInterface coreInterface, byte[] mac);

    //Delete link object in core
    //TT
    public native void HMBTCorelinkDisconnect(HMBTCoreInterface coreInterface, byte[] mac);

    //Forward link incoming data to core
    //TT
    public native void HMBTCorelinkIncomingData(HMBTCoreInterface coreInterface, byte[] data, int
            size, byte[] mac, int characteristic);

    public native void HMBTCorelinkWriteResponse(HMBTCoreInterface coreInterface, byte[] mac, int
            characteristic);

    public native void HMBTCoreSendCustomCommand(HMBTCoreInterface coreInterface, byte[] data,
                                                 int size, byte[] mac);

    public native void HMBTCoreSendReadDeviceCertificate(HMBTCoreInterface coreInterface, byte[]
            mac, byte[] nonce, byte[] caSignature);

    public native void HMBTCoreSendRegisterAccessCertificate(HMBTCoreInterface coreInterface,
                                                             byte[] certificate);

    //crypto
    public native void HMBTCoreCryptoCreateKeys(byte[] privateKey, byte[] publicKey);

    public native void HMBTCoreCryptoAddSignature(byte[] data, int size, byte[] privateKey,
                                                  byte[] signature);

    public native int HMBTCoreCryptoValidateSignature(byte[] data, int size, byte[] pubKey,
                                                      byte[] signature);

    //HMBTCoreInterfaceImpl
    public native void HMBTCoreTelematicsReceiveData(HMBTCoreInterface coreInterface, int length,
                                                     byte[] data);

    public native void HMBTCoreSendTelematicsCommand(HMBTCoreInterface coreInterface, byte[]
            serial, byte[] nonce, int length, byte[] data);
}

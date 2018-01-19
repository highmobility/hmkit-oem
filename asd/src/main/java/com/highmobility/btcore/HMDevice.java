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

/**
 * Created by ttiganik on 13/04/16.
 */
public class HMDevice {
    byte[] _mac = {0,0,0,0,0,0};
    byte[] _serial = {0,0,0,0,0,0,0,0,0};
    byte[] _appId = {0,0,0,0,0,0,0,0,0,0,0,0};
    int _isAuthorised = 0;

    public byte[] getMac() {
        return _mac;
    }

    public void setMac(byte[] mac) {
        copyBytesToJNI(mac,_mac);
    }

    public byte[] getSerial() {
        return _serial;
    }

    public void setSerial(byte[] serial) {
        copyBytesToJNI(serial,_serial);
    }

    public int getIsAuthenticated() {
        return _isAuthorised;
    }

    public void setIsAuthenticated(int isAuthorised) {
        _isAuthorised = isAuthorised;
    }

    public byte[] getAppId() {
        return _appId;
    }

    public void setAppId(byte[] appId) {
        copyBytesToJNI(appId,_appId);
    }

    private void copyBytesToJNI(byte[] from, byte[] to) {
        for (int i = 0; i < from.length; i++) {
            to[i] = from[i];
        }
    }
}

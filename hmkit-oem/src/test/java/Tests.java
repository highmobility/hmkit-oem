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
import com.highmobility.crypto.AccessCertificate;
import com.highmobility.crypto.Crypto;
import com.highmobility.crypto.value.DeviceSerial;
import com.highmobility.crypto.value.PrivateKey;
import com.highmobility.crypto.value.PublicKey;
import com.highmobility.crypto.value.Signature;
import com.highmobility.hmkit.CryptoException;
import com.highmobility.hmkit.HMKit;
import com.highmobility.value.Bytes;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class Tests {
    // only work on linux
    @Test public void testSignatures() {
        PublicKey publicKey = new PublicKey
                ("A5A74048A85AC52A2E41DE5F9554C9CC36B6E3721EE8E8CE9169DC54192D17FD52C3BD1A4AE7F592756C083E17E54B7730965D99B238EB8D33B172DC35E32398");
        PrivateKey privateKey = new PrivateKey
                ("1B8593D0478B9017C2427256AAEE25FF8A4E20EC6611AFE31D52B32CE0BECCA2");

        Crypto crypto = HMKit.getInstance().getCrypto();

        Bytes data = new Bytes(new byte[]{0x02, 0x03});
        Signature sig = crypto.sign(data, privateKey);

        assertTrue(crypto.verify(data, sig, publicKey));

        publicKey = new PublicKey(
                "A6A74048A85AC52A2E41DE5F9554C9CC36B6E3721EE8E8CE9169DC54192D17FD52C3BD1A4AE7F592756C083E17E54B7730965D99B238EB8D33B172DC35E32398");
        assertTrue(crypto.verify(data, sig, publicKey) == false);
    }

    @Test public void testCommand() throws CryptoException {
        PrivateKey vehiclePrivateKey = new PrivateKey(
                "468A685967EF57ADC7FB6C51B12045722C74277C45EDD8EC005D1FF4197D6006");
        AccessCertificate vehicleCertificate = new AccessCertificate(
                "01746D63730908070605040302010102030405060708096A94B494B0BD287E9C98014CECC1E3E19F30C002B74116BB727B93FB422DBDC12172A3FD24C36EB2ABEC57AA94D74A53A393D7B0AE30E6131B1EEDBCA3A17530110101010111020201010301020310937CB737CD8EA3E5E510830A54D2945F5BD8C54A1486489D7E8B911B06ABC1CE12B1D1B4E9994D99987106C63730919E5630FFBD755CF00AD62ABA1AC53983");
        Bytes nonce = new Bytes("AABBCCDDEEFF000000");
        DeviceSerial vehicleSerial = new DeviceSerial("090807060504030201");
        Bytes command = new Bytes("AABB");

        Bytes encryptedCommand = HMKit.encryptCommand(vehiclePrivateKey,
                vehicleCertificate, nonce, vehicleSerial, command);
        Bytes decryptedCommand = HMKit.decryptCommand(vehiclePrivateKey,
                vehicleCertificate, encryptedCommand);
        assertTrue(encryptedCommand.equals(command) == false);
        assertTrue(decryptedCommand.equals(command));
    }
}

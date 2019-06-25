
import com.highmobility.crypto.AccessCertificate;
import com.highmobility.crypto.Crypto;
import com.highmobility.crypto.value.PrivateKey;
import com.highmobility.crypto.value.PublicKey;
import com.highmobility.crypto.value.Signature;
import com.highmobility.hmkit.HMKit;
import com.highmobility.value.Bytes;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestSignatures {
    // only work on linux
    @Test public void testSignatures() {
        Crypto crypto = HMKit.getInstance().crypto;
        PublicKey publicKey = new PublicKey
                ("A5A74048A85AC52A2E41DE5F9554C9CC36B6E3721EE8E8CE9169DC54192D17FD52C3BD1A4AE7F592756C083E17E54B7730965D99B238EB8D33B172DC35E32398");
        PrivateKey privateKey = new PrivateKey
                ("1B8593D0478B9017C2427256AAEE25FF8A4E20EC6611AFE31D52B32CE0BECCA2");
        assertTrue(true);

        Bytes data = new Bytes("0203");
        Signature sig = crypto.sign(data, privateKey);

        assertTrue(crypto.verify(data, sig, publicKey));

        publicKey = new PublicKey("A6A74048A85AC52A2E41DE5F9554C9CC36B6E3721EE8E8CE9169DC54192D17FD52C3BD1A4AE7F592756C083E17E54B7730965D99B238EB8D33B172DC35E32398");
        assertTrue(crypto.verify(data, sig, publicKey) == false);

        // TODO: 2019-06-17 test the telematics command as well
    }

    @Test public void testCommand() {
        HMKit kit = HMKit.getInstance();
        Bytes command = new Bytes("AABB");
        Bytes nonce = new Bytes("AABBCCDDEEFF000000");
        PrivateKey vehiclePrivateKey = new PrivateKey("468A685967EF57ADC7FB6C51B12045722C74277C45EDD8EC005D1FF4197D600");

        AccessCertificate vehicleCertificate = new AccessCertificate("01746D63730908070605040302010102030405060708096A94B494B0BD287E9C98014CECC1E3E19F30C002B74116BB727B93FB422DBDC12172A3FD24C36EB2ABEC57AA94D74A53A393D7B0AE30E6131B1EEDBCA3A17530110101010111020201010301020310937CB737CD8EA3E5E510830A54D2945F5BD8C54A1486489D7E8B911B06ABC1CE12B1D1B4E9994D99987106C63730919E5630FFBD755CF00AD62ABA1AC53983");
        /*
         * @param privateKey  the vehicle's private key
         * @param certificate the vehicle's Access Certificate
         * @param nonce       the nonce
         * @param serial      the vehicle's serial number
         * @param command     the command that will be encrypted
         */

        kit.encryptCommand()

    }
}

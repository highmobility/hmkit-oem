import com.highmobility.crypto.Crypto;
import com.highmobility.utils.Bytes;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestSignatures {
    @Test public void testSignatures() {
        byte[] publicKey = Bytes.bytesFromHex("A5A74048A85AC52A2E41DE5F9554C9CC36B6E3721EE8E8CE9169DC54192D17FD52C3BD1A4AE7F592756C083E17E54B7730965D99B238EB8D33B172DC35E32398");
        byte[] privateKey = Bytes.bytesFromHex("1B8593D0478B9017C2427256AAEE25FF8A4E20EC6611AFE31D52B32CE0BECCA2");

        assertTrue(true);

        byte[] data = new byte[] { 0x02, 0x03 };
        byte[] sig = Crypto.sign(data, privateKey);

        assertTrue(Crypto.verify(data, sig, publicKey));

        publicKey[2] = 0x00;
        assertTrue(Crypto.verify(data, sig, publicKey) == false);
    }
}
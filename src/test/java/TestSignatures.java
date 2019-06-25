
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
}

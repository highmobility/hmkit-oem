import com.highmobility.crypto.Crypto;
import com.highmobility.crypto.KeyPair;

public class Main {
    public static void main(String[] args) {
        KeyPair pair = Crypto.createKeypair();
        System.out.println(pair.getPrivateKeyBase64());
        System.out.println(pair.getPublicKeyBase64());
    }
}

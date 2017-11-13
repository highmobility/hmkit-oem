import com.highmobility.autoapi.Command;
import com.highmobility.autoapi.incoming.LightsState;
import com.highmobility.crypto.Crypto;
import com.highmobility.crypto.KeyPair;
// TODO: delete autoapi lib
class Test {
    RealCar car = RealCar.find("1FUJA6BD92LJ99712");

    private void createKeyPairs() {
        // Pseudo code about a possible implementation to initialize and map HM keys to a real car.
        // find an existing car

        // check if HM keys exist for this vehicle
        if (car.HMKeysExist() == false) {
            // if not, create HM keys and add them to the real car.
            byte[] serialNumber = Crypto.createSerialNumber();
            KeyPair keypair = Crypto.createKeypair();
            byte[] privateKey = keypair.getPrivateKey();
            byte[] publicKey = keypair.getPublicKey();
            car.setHMKeys(serialNumber, privateKey, publicKey);
            car.save();
        }
    }

    private void sendCommand() {

        // Pseudo code about receiving a lock status change from the car and forwarding this message to High-Mobility.
        boolean waitingForCommands = true;

        while (waitingForCommands == true) {
            LockStatusChange lockStatusChange = car.waitForLockStatusCommand();
            if (lockStatusChange != null) waitingForCommands = false;
        }

        // create an AutoAPI command from the command received from the car
    }
}

class LockStatusChange {
    boolean doorsLocked;
}

class RealCar {
    static RealCar find(String VIN) { return new RealCar(); }

    boolean HMKeysExist() { return false; }
    void setHMKeys(byte[] serialNumber, byte[] privateKey, byte[] publicKey){}
    void save() {}
    LockStatusChange waitForLockStatusCommand() {return null;}
}

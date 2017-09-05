package com.highmobility;

import com.highmobility.btcore.HMBTCoreInterface;
import com.highmobility.btcore.HMDevice;

public class CoreCallback implements HMBTCoreInterface {

    public int HMBTHalInit(){
        return 0;
    }

    //Start stop central scanning
    public int HMBTHalScanStart(){
        return 0;
    }
    public int HMBTHalScanStop(){
        return 0;
    }

    //Start stop peripheral advertisement
    //TT
    public int HMBTHalAdvertisementStart(byte[] issuer, byte[] appID){
        return 0;
    }

    public int HMBTHalAdvertisementStop(){
        return 0;
    }

    //Connect disconnect to peripheral
    public int HMBTHalConnect(byte[] mac){
        return 0;
    }
    public int HMBTHalDisconnect(byte[] mac){
        return 0;
    }

    //Start peripheral service discovery
    public int HMBTHalServiceDiscovery(byte[] mac){
        return 0;
    }

    //Write data to peripheral or central
    //TT
    public int HMBTHalWriteData(byte[] mac, int length, byte[] data, int characteristic){
        return 0;
    }
    //Read data from peripheral
    public int HMBTHalReadData(byte[] mac, int offset, int characteristic){
        return 0;
    }

    public int HMBTHalTelematicsSendData(byte[] issuer, byte[] serial, int length, byte[] data){
        return 0;
    }

    //PERSISTENCE HAL

    //Get current device serial number
    //TT
    public int HMPersistenceHalgetSerial(byte[] serial){
        return 0;
    }
    //Get current device publick key
    //TT
    public int HMPersistenceHalgetLocalPublicKey(byte[] publicKey){
        return 0;
    }
    //Get current device publick key
    //TT
    public int HMPersistenceHalgetLocalPrivateKey(byte[] privateKey){
        return 0;
    }
    //TT
    public int HMPersistenceHalgetDeviceCertificate(byte[] cert){
        return 0;
    }

    public int HMPersistenceHalgetCaPublicKey(byte[] cert){
        return 0;
    }

    public int HMPersistenceHalgetOEMCaPublicKey(byte[] cert){
        return 0;
    }

    //Add remote device publick key to storagr
    //TT
    public int HMPersistenceHaladdPublicKey(byte[] serial, byte[] publicKey, byte[] startDate, byte[] endDate, int commandSize, byte[] command){
        return 0;
    }
    //Get remote device publick key from storage
    //TT
    public int HMPersistenceHalgetPublicKey(byte[] serial, byte[] publicKey, byte[] startDate, byte[] endDate, int[] commandSize, byte[] command){
        return 0;
    }
    //TT
    public int HMPersistenceHalgetPublicKeyByIndex(int index, byte[] serial, byte[] publicKey, byte[] startDate, byte[] endDate, int[] commandSize, byte[] command){
        return 0;
    }
    //TT
    public int HMPersistenceHalgetPublicKeyCount(int[] count){
        return 0;
    }
    //Remove remote device publick key from storage
    //TT
    public int HMPersistenceHalremovePublicKey(byte[] serial){
        return 0;
    }

    //Add certificate to storage
    //TT
    public int HMPersistenceHaladdStoredCertificate(byte[] cert, int size){
        return 0;
    }
    //Get certificate from storage
    //TT
    public int HMPersistenceHalgetStoredCertificate(byte[] serial, byte[] cert, int[] size){
        return 0;
    }
    //Delete certificate from storage
    //TT
    public int HMPersistenceHaleraseStoredCertificate(byte[] serial){
        return 0;
    }


    //Proximity
    //TT
    public void HMApiCallbackEnteredProximity(HMDevice device){

    }
    //TT
    public void HMApiCallbackExitedProximity(HMDevice device){

    }

    //Callback
    //TT
    public void HMApiCallbackCustomCommandIncoming(HMDevice device, byte[] data, int length){

    }
    public void HMApiCallbackCustomCommandResponse(HMDevice device, byte[] data, int length){

    }
    public int HMApiCallbackGetDeviceCertificateFailed(HMDevice device, byte[] nonce){
        return 0;
    }
    public int HMApiCallbackPairingRequested(HMDevice device){
        return 0;
    }

    public void HMApiCallbackTelematicsCommandIncoming(HMDevice device, int id, int length, byte[] data){

    }

    //Crypto
    public void HMCryptoHalGenerateNonce(byte[] nonce){

    }
}

package com.highmobility;

import com.highmobility.btcore.HMBTCore;


public class Main {

    public static void main(String[] args) {
		CoreCallback callback = new CoreCallback();
		HMBTCore core = new HMBTCore();
		core.HMBTCoreInit(callback);


	}
}

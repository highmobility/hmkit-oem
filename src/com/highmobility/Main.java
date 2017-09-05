package com.highmobility;

import com.highmobility.btcore.*;

public class Main {

    public static void main(String[] args) {
	System.out.print("HELLO");

	CoreCallback callback = new CoreCallback();

	HMBTCore core = new HMBTCore();

	core.HMBTCoreInit(callback);

    }
}

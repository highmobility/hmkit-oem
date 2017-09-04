package com.highmobility.oemcloudsdk

import java.net.URL
import java.net.URLConnection

class HMServer {
    external fun helloFromC()

    interface Listener {
        fun onCommandReceived(command:ByteArray)
    }

    var listener:Listener? = null

    fun connect(accessToken:String) {

        val conn:URLConnection = URL("").openConnection()
    }
}

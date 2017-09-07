package com.xjtu.kangy.WereWolf.utils

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.Socket

/**
 * Created by kangy on 2017/9/5.
 */

class ConnectionThread(internal var mSocket: Socket) : Thread() {
    lateinit var res: String
    override fun run() {
        while (Data.running)
            try {
                val inputStream = mSocket.getInputStream()
                val outputStream = mSocket.getOutputStream()
                while (inputStream.available() > 0) {
                    res = BufferedReader(InputStreamReader(inputStream)).readLine()
                }
                synchronized(Data.receivedMsgList) {
                    val event = Event(res, mSocket.inetAddress)
                    Data.receivedMsgList.add(event)
                }
                for (event in Data.sendMsgList) {
                    if (mSocket.inetAddress == event.ip) {
                        outputStream.write(event.msg.toByteArray())
                        synchronized(Data.sendMsgList) {
                            Data.sendMsgList.remove(event)
                        }
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
    }
}

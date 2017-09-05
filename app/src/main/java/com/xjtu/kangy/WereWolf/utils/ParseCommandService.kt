package com.xjtu.kangy.WereWolf.utils

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket

/**
 * Created by kangy on 2017/9/5.
 */
class ParseCommandService : Service() {
    companion object {
        var chating = false
    }

    override fun onCreate() {
        while (chating) {
            if (Data.ipMap.size != 0) {
                for (socket in Data.ipMap.values) {
                    var res = getString(socket)
                    if (res.isNotEmpty()) {
                        var intent = Intent(Data.SENDMSG_ACTION)
                        intent.putExtra("msg", res)
                        intent.putExtra("ip", socket.inetAddress)
                        sendBroadcast(intent)
                    }
                }
            }
        }
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getString(socket: Socket): String {
        if (socket.isConnected && !socket.isInputShutdown) {
            val inputStream = socket.getInputStream()
            if (inputStream.available() > 0) {
                val order = BufferedReader(InputStreamReader(inputStream)).readLine()
                return order
            }
            return ""
        }
        return ""
    }

}
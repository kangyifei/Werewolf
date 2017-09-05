package com.xjtu.kangy.WereWolf.utils

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.io.IOException
import java.net.ServerSocket

/**
 * Created by kangy on 2017/9/4.
 */

class TcpServer @Throws(IOException::class)
constructor(port: Int) : Service() {
    internal var mServerSocket: ServerSocket

    init {
        this.mServerSocket = ServerSocket(port)
    }


    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        while (true) {
            try {
                val socket = mServerSocket.accept()
                if (!Data.ipList.contains(socket.inetAddress)) {
                    Data.ipList[Data.ipMap.size] = socket.inetAddress
                    Data.ipMap[socket.inetAddress] = socket
                } else {
                    Data.ipMap[socket.inetAddress] = socket
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }


        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}

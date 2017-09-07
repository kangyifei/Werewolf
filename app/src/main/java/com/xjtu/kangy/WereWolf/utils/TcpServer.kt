package com.xjtu.kangy.WereWolf.utils

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import java.io.IOException
import java.net.ServerSocket
import java.net.Socket

/**
 * Created by kangy on 2017/9/4.
 */

class TcpServer @Throws(IOException::class)
constructor(port: Int = 3057) : Service() {
    internal var mServerSocket: ServerSocket

    init {
        this.mServerSocket = ServerSocket(port)
    }

    private var socket: Socket = Socket()

    private val mHandler = object : Handler() {
        override fun dispatchMessage(msg: Message) {
            when (msg.what) {
                1 -> socket = msg.obj as Socket
            }
        }

    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        while (Data.running) {
            try {
                val size = Data.ipMap.size
                Thread {
                    val socket = mServerSocket.accept()
                    var msg = Message()
                    msg.obj = socket
                    msg.what = 1
                    mHandler.sendMessage(msg)
                }.start()
                if (!Data.ipList.contains(socket.inetAddress)) {
                    Data.ipList[Data.ipMap.size] = socket.inetAddress
                    Data.ipMap[socket.inetAddress] = socket
                } else {
                    Data.ipMap[socket.inetAddress] = socket
                }
                val newSize = Data.ipMap.size
                if (newSize > size) {
                    for (i in size..newSize) {
                        val thread = ConnectionThread(Data.ipMap[Data.ipList[i - 1]]!!)
                        thread.start()
                    }
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

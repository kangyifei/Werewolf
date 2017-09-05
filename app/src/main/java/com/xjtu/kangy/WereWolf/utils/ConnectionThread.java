package com.xjtu.kangy.WereWolf.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by kangy on 2017/9/5.
 */

public class ConnectionThread extends Thread {
    Socket mSocket;

    public ConnectionThread(Socket socket) {
        this.mSocket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = mSocket.getInputStream();
            OutputStream outputStream = mSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

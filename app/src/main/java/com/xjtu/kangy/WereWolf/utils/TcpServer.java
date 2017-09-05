package com.xjtu.kangy.WereWolf.utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kangy on 2017/9/4.
 */

public class TcpServer {
    ServerSocket mServerSocket;

    public TcpServer(int port) throws IOException {
        this.mServerSocket = new ServerSocket(port);
    }

    void connect() {
        try {
            Socket socket = mServerSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

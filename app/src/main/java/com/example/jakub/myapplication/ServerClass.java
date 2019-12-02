package com.example.jakub.myapplication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerClass extends Thread{
    private MainActivity mainActivity;
    Socket socket;
    ServerSocket serverSocket;

    public ServerClass(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(8888);
            socket = serverSocket.accept();
            mainActivity.sendReceive = new SendReceive(mainActivity, socket);
            mainActivity.sendReceive.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

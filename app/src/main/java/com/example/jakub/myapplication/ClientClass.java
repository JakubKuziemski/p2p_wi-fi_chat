package com.example.jakub.myapplication;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientClass extends Thread{
    private MainActivity mainActivity;
    Socket socket;
    String hostAdd;

    public ClientClass(MainActivity mainActivity, InetAddress hostAddress){
        this.mainActivity = mainActivity;
        hostAdd = hostAddress.getHostAddress();
        socket = new Socket();
    }

    @Override
    public void run() {
        try {
            socket.connect(new InetSocketAddress(hostAdd, 8888), 500);
            mainActivity.sendReceive = new SendReceive(mainActivity, socket);
            mainActivity.sendReceive.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.example.jakub.myapplication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SendReceive extends Thread{
    private MainActivity mainActivity;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public SendReceive(MainActivity mainActivity, Socket skt){
        this.mainActivity = mainActivity;
        socket = skt;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //rozdzoielic na 2 klasy jedna wysyla druga odbiera

    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        int bytes;

        while (socket != null){
            try {
                bytes = inputStream.read(buffer);
                if(bytes > 0){
                    mainActivity.handler.obtainMessage(MainActivity.MESSAGE_READ, bytes, -1, buffer).sendToTarget();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(byte[] bytes){
        try {
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

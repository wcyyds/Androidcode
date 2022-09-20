package com.example.mytalk02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RecevieThread implements Runnable{
    Socket socket = null;
    String him = null;
    public RecevieThread (Socket socket,String him) {
        this.socket = socket;
        this.him = him;
    }
    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String str = br.readLine();
                System.out.println(str);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

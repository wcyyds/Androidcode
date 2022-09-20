package com.example.mytalk02;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class SendThread implements Runnable{
    Socket socket = null;
    String me = null;
    public SendThread(Socket socket,String me) {
        this.socket = socket;
        this.me =me;
    }

    @Override
    public void run() {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            while (true){
                Scanner scanner = new Scanner(System.in);
                bw.write(me + scanner.nextLine());
                bw.write("\n");
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.example.mytalk;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * 发送线程
 */
public class SendThread  {
//    Socket socket = null;
//    String me = null;
//    public SendThread(Socket socket,String me) {
//        this.socket = socket;
//        this.me =me;
//    }
//
//    @Override
//    public void run() {
//        try {
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            while (true){
//                System.out.println("["+me+"想说]：");
//                Scanner scanner = new Scanner(System.in);
//                bw.write(scanner.nextLine());
//                bw.write("\n");
//                bw.flush();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}


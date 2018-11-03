package com.sharif;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class server {

    public static ArrayList<Socket> list = new ArrayList<Socket>();

    public void sharif(Socket socket)
    {
       new Thread(){
            @Override
            public void run() {
                if(socket!=null)
                {
                    list.add(socket);
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String str = null;
                        while((str=reader.readLine())!=null)
                        {
                            System.out.println(Thread.currentThread()+">>>>"+str);
                            for(Socket singleSocket:list)
                            {
                                BufferedWriter writer =  new BufferedWriter(new OutputStreamWriter(singleSocket.getOutputStream()));
                                writer.write(Thread.currentThread()+">>>>"+str+"\r\n");
                                writer.flush();
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1111);
            while (true)
            {
                new server().sharif(serverSocket.accept());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

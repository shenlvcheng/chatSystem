package com.sharif;

import java.io.*;
import java.net.Socket;

public class Client {

    public void sharif()
    {
        Socket socket =null;
        byte[] bytearray = new byte[1024];
        try {
            socket = new Socket("127.0.0.1",1111);
            OutputStream output = socket.getOutputStream();
            InputStream input = socket.getInputStream();
            read(input);
            while(true)
            {
                System.out.print("输入要说的话：\r\n");
                InputStream is = System.in;
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String result = reader.readLine();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
                writer.write(result+"\r\n");
                writer.flush();

                output.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void read(InputStream input)
    {
        new Thread()
        {
            @Override
            public void run() {
                BufferedReader reader1 = new BufferedReader(new InputStreamReader(input));
                String str = null;
                try {
                    while((str=reader1.readLine())!=null)
                    {
                        System.out.println("系统回答："+str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }


    public static void main(String[] args) {
        new Client().sharif();
    }
}

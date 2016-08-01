package com.lidar_pro.main.Handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static com.lidar_pro.main.Helpers.GlobalVars.*;

/**
 * Created by Iwan on 27.07.2016.
 */
public class SocketDialog extends Thread implements Runnable {

    private Socket serverSocket = null;

    public static BufferedReader in = null;
    public static PrintWriter out = null;
    public BufferedReader consoleInput = null;

    public static String fromUser = null;
    public static String fromServer = null;

    /** Communication algorithm:
     *
     * consoleInput -> out;
     * server reads & generating serverData;
     * in <- serverData.
     *
     */

    // Конструктор нужен, чтобы создавать экземпляр треда
    public SocketDialog(String ip, int port) {
        try {
            serverSocket = new Socket(ip, port);

            in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream())); // get info from server
            out = new PrintWriter(serverSocket.getOutputStream(), true); // send info to server
            consoleInput = new BufferedReader(new InputStreamReader(System.in)); // write string in console
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void request_abs(String str) {
        try {
            out.println(str);
            fromServer = in.readLine();
            System.out.println(fromServer);
            String s[] = fromServer.split(",");
            abs_fileName = sharedPath + s[0];
            abs_ms_fileName = sharedPath + s[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void request_snr(String str) {
        try {
            out.println(str);
            fromServer = in.readLine();
            System.out.println(fromServer);
            snr_fileName = sharedPath + fromServer;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                if ((fromUser = consoleInput.readLine()) != null) {
                    out.println(fromUser);
                    fromServer = in.readLine();
                    System.out.println(fromServer);
                }
            }
            /*out.close();
            in.close();
            consoleInput.close();
            serverSocket.close();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}








































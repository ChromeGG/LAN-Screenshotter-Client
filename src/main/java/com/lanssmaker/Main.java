package com.lanssmaker;

import com.lanssmaker.client.SocketClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        System.getProperty("line.separator");

        SocketClient client = new SocketClient();
        String serverIP = getIpFromTxt();
        client.startConnection(serverIP, 2345);
    }

    private static String getIpFromTxt() throws FileNotFoundException {
        Scanner inputTxt = new Scanner(new File("serverIP.txt"));
        return inputTxt.nextLine();
    }

}
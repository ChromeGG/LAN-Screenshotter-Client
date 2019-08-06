package com.lanssmaker.client;

import com.lanssmaker.screenShoter.SSmaker;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.Socket;
import java.util.Base64;

public class SocketClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) {


        while (true) {
            try {
                clientSocket = new Socket(ip, port);
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                //ignore
            }

            if (clientSocket != null) {
                waitForInstructions();
            }
        }
    }

    private void waitForInstructions() {

        String choose = null;

        boolean connected = true;

        while (connected) {

            try {
                choose = in.readLine();
            } catch (IOException e) {

                connected = false;
            }


            if (choose != null) {
                switch (choose) {
                    case "1":
                        SSmaker robot = new SSmaker();
                        BufferedImage screen = robot.screenCapture();
                        String convertedScreen = imgToBase64String(screen);
                        out.println(convertedScreen);
                        break;
                    case "2":
                        out.println(clientSocket.getInetAddress());
                        break;
                    default:
                        System.err.println("Switch ERROR");
                }
            }
        }
    }

    private static String imgToBase64String(final RenderedImage img) {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, "jpg", os);
            return Base64.getEncoder().encodeToString(os.toByteArray());
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}


package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 8989;
    private static String city = null;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    if (city == null) {
                        out.println("???");
                        city = in.readLine();
                        out.println("Ок");
                    } else {
                        //out.println("Предыдущий ввод:");
                        out.println(city);
                        char lastLetter = city.charAt(city.length() - 1);
                        String response = in.readLine();
                        if ((response.charAt(0) == lastLetter)) {
                            out.println("OK");
                            city = response;
                        } else {
                            out.println("Not ok");
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

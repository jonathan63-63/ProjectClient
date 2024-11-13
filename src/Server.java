package src;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server is listening on port 1234");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                User user = new User("admin", "admin", "admin", "admin");
                ClientHandle clientHandle = new ClientHandle(socket, user);
                Thread thread = new Thread(clientHandle);
                thread.start();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}


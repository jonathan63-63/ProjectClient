package src;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private User user;
    ServerConnector sc;
    ExecutorService threadPool;
    private String host;
    private int port;

    public Client()
    {

        threadPool = Executors.newFixedThreadPool(10);
    }

    public void setHostAndPort(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void handleClient() throws IOException {
        threadPool.execute(new ClientHandle(socket, user));
    }

    public void shutdown()
    {
        threadPool.shutdown();
    }

    public void sendMessage(String message) throws IOException
    {
        sc.sendMessage(message);
    }


    @Override
    public void run()
    {
        try
        {
            socket = new Socket(host, port);
            sc = new ServerConnector(socket);

            sc.connect();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


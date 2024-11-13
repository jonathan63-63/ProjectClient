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

    public Client()
    {

        threadPool = Executors.newFixedThreadPool(10);
    }


    // Ska handleClient() vara en del av Client-klassen? eller i server?
    // För när en person connectar till server får den en client no???
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
    public void run() // Hur gör vi med run() metoden? Hur ska ta vi emot meddelanden från servern?
    {
        try
        {
            socket = new Socket("localhost", 1234);
            sc = new ServerConnector(socket);

            sc.connect("localhost", 1234);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


package src;

import java.io.IOException;

public class ClientFacade {
    private final Client client;
    private Thread clientThread;
    public ClientFacade()
    {
        this.client = new Client();
    }

    public void connect(String host, int port) throws IOException {
        client.setHostAndPort(host, port);
        if (clientThread == null || !clientThread.isAlive()) {
            clientThread = new Thread(client);
            clientThread.start();
        }
    }
    public void disconnect() throws IOException
    {
        client.sc.disconnect();
    }

    public void sendMessage(String message) throws IOException
    {
        client.sendMessage(message);
    }

    public void start(){
        new Thread(client).start();
    }

}

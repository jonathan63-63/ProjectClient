package src;

import java.io.IOException;

public class ClientFacade {
    private final Client client;
    public ClientFacade()
    {
        this.client = new Client();
    }

    public void connect() throws IOException {
        client.sc.connect();
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

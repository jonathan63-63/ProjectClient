package src;

import java.io.*;
import java.net.Socket;

public class ServerConnector {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public ServerConnector(Socket socket)
    {
        this.socket = socket;
        try
        {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException _) {

        }
    }

    public void connect(String host, int port) throws IOException
    {
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public void disconnect() throws IOException
    {
        if(socket != null && !socket.isClosed()) socket.close();
    }


}

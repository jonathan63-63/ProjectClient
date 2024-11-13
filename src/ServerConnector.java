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
        } catch (IOException e) {

        }
    }

    public void disconnect() throws IOException
    {
        if(socket != null && !socket.isClosed()) socket.close();
    }

    // to server!
    public void sendMessage(String message) throws IOException
    {
        out.write(message + "\n");
        out.flush();
        System.out.println("Message sent to server: " + message);
    }
    // receive from server
    public String receiveMessage() throws IOException
    {
        return in.readLine();
    }


}

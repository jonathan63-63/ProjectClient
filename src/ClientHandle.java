package src;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandle implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private User user;
    private ArrayList<ClientHandle> clientHandlers;

    public ClientHandle(Socket socket, User user) throws IOException
    {
        this.socket = socket;
        this.user = user;

        try
        {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException _) {

        }
    }
    @Override
    public void run()
    {
        String message;// Message protocol får implementeras
            try
            {
                while ((message = in.readLine()) != null) // från client
                { // hantera meddelande

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}

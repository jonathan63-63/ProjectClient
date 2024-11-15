package src;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandle implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private User user;
    private static ArrayList<ClientHandle> clientHandlers = new ArrayList<>();
    static Message messageProtocol = new Message("admin", "admin");

    public ClientHandle(Socket socket, User user) throws IOException
    {
        this.socket = socket;
        this.user = user;

        try
        {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            clientHandlers.add(this);
        } catch (IOException e) {

        }
    }
    @Override
    public void run()
    {
        String message;
            try
            {
                while ((message = in.readLine()) != null) // from client
                { // hantera meddelande
                    System.out.println("Message received from client: " + message);
                    handleMessage(message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void sendToAllClients(String message)
        {
            for(ClientHandle client : clientHandlers)
            {
                try
                {
                    client.out.write(message + "\n");
                    client.out.flush();
                    System.out.println("Message sent to client: " + message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void handleMessage(String message)
        {
            // Pattern match on message, implement more later
            Message parsedMessage = messageProtocol.parseMessage(message);
            if(!parsedMessage.getType().equals("message"))
            {
                switch(parsedMessage.getType())
                {
                    case "nick":
                        user.setNewUserName(parsedMessage.getMessage());
                        break;
                    case "join":

                        break;
                    case "leave":

                        break;

                }
            } else
            {
                sendToAllClients(user.getUsername() + ": " + parsedMessage.getMessage());
            }

        }
}

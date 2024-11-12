package src;

public class Message {
    private String message;
    private String type;

    public Message(String type, String message)
    {
        this.type = type;
        this.message = message;
    }

    public Message parseMessage(String message)
    {
        if(message.startsWith("/"))
        {
            String[] string = message.split(" ", 2);
            String command = string[0].substring(1);
            String arg = string[1];
            return new Message(command, arg);
        } else
        {
            return new Message("message", message);
        }
    }

}

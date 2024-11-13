package src;

import java.io.IOException;
import java.util.Scanner;

public class ClientCLI {
    public static void main(String[] args) throws IOException {
            ClientFacade clientFacade = new ClientFacade();
            Scanner scanner = new Scanner(System.in);

            try {
                clientFacade.connect("localhost", 1234);

                while (true) {
                    System.out.println("Enter a message to send (or 'exit' to quit):");
                    String message = scanner.nextLine();

                    if (message.equalsIgnoreCase("exit")) {
                        clientFacade.disconnect();
                        break;
                    }

                    clientFacade.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                scanner.close();
            }
    }
}

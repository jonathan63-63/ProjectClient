package src;

import java.io.IOException;
import java.util.Scanner;

public class ClientCLI {
    public static void main(String[] args) throws IOException {
        ClientFacade clientFacade = new ClientFacade();
        Scanner scanner = new Scanner(System.in);

        clientFacade.connect();
        clientFacade.start();

        while (true) {
            System.out.println("Enter message or quit with 'exit' ");
            String message = scanner.nextLine();

            if (message.equalsIgnoreCase("exit")) {
                clientFacade.disconnect();
                break;
            }

            clientFacade.sendMessage(message);
        }

        scanner.close();
    }
}
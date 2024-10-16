package 软件体系结构实验三.三层CS结构;

import java.io.*;
import java.net.*;

public class Client {
    private String hostname;
    private int port;

    public Client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void start() {
        try (Socket socket = new Socket(hostname, port);
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            String userInput;
            while ((userInput = br.readLine()) != null) {
                oos.writeObject(userInput);
                oos.flush();

                Object response = ois.readObject();
                System.out.println("Server response: " + response);

                if ("exit".equalsIgnoreCase(userInput.trim())) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 1234);
        client.start();
    }
}

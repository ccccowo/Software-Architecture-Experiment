package 软件体系结构实验三.二层CS结构;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private String hostname;
    private int port;

    public Client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void start() {
        try (Socket socket = new Socket(hostname, port);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Enter commands (ADD:name:address:phone, UPDATE:name:address:phone, DELETE:name, BYE to quit):");
            while (true) {
                System.out.print("> ");
                String userInput = br.readLine();
                oos.writeObject(userInput);
                oos.flush();

                String response = (String) ois.readObject();
                System.out.println("Server response: " + response);

                if ("BYE".equals(response)) {
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
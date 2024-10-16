package 软件体系结构实验三.三层CS结构;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running and waiting for client connection...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected.");

                try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                     ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {

                    Object inputObject = ois.readObject();
                    System.out.println("Client says: " + inputObject);

                    String response = handleClientRequest((String) inputObject);
                    oos.writeObject(response);
                    oos.flush();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String handleClientRequest(String request) {
        // Simple request handler for demonstration purposes
        if ("exit".equalsIgnoreCase(request.trim())) {
            return "Server is shutting down.";
        }
        return "Hello, client!";
    }

    public static void main(String[] args) {
        Server server = new Server(1234);
        server.start();
    }
}
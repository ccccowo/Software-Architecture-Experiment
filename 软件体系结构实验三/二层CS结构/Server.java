package 软件体系结构实验三.二层CS结构;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private int port;
    private Map<String, Contact> addressBook = new HashMap<>();

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

                    String response = "";
                    while (!(response = handleClientRequest(ois)).equals("BYE")) {
                        oos.writeObject(response);
                        oos.flush();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found exception");
            e.printStackTrace();
        }
    }

    private String handleClientRequest(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        String request = (String) ois.readObject();
        System.out.println("Client says: " + request);

        if (request.equals("BYE")) {
            return "BYE";
        }

        String[] parts = request.split(":");
        String command = parts[0];

        switch (command) {
            case "ADD":
                return addContact(parts);
            case "UPDATE":
                return updateContact(parts);
            case "DELETE":
                return deleteContact(parts);
            default:
                return "Unknown command";
        }
    }

    private String addContact(String[] parts) {
        if (parts.length != 4) {
            return "Invalid add request";
        }
        String name = parts[1].trim();
        String address = parts[2].trim();
        String phone = parts[3].trim();
        if (addressBook.containsKey(name)) {
            return "Contact already exists";
        }
        addressBook.put(name, new Contact(name, address, phone));
        return "Contact added successfully";
    }

    private String updateContact(String[] parts) {
        if (parts.length != 4) {
            return "Invalid update request";
        }
        String name = parts[1].trim();
        String address = parts[2].trim();
        String phone = parts[3].trim();
        Contact contact = addressBook.get(name);
        if (contact == null) {
            return "Contact not found";
        }
        contact.address = address;
        contact.phone = phone;
        return "Contact updated successfully";
    }

    private String deleteContact(String[] parts) {
        if (parts.length != 2) {
            return "Invalid delete request";
        }
        String name = parts[1].trim();
        if (!addressBook.containsKey(name)) {
            return "Contact not found";
        }
        addressBook.remove(name);
        return "Contact deleted successfully";
    }

    public static void main(String[] args) {
        Server server = new Server(1234);
        server.start();
    }

    static class Contact {
        String name;
        String address;
        String phone;

        Contact(String name, String address, String phone) {
            this.name = name;
            this.address = address;
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "Contact{" +
                    "name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }
}
package BankSystem;

import BankSystem.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static final int PORT=12345;
    private static final ConcurrentHashMap<String,Integer> bank = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        System.out.println("Server on port: "+PORT);
        try{
            ServerSocket serverSocket=new ServerSocket(PORT);
            while(true){
                Socket clientSocket=serverSocket.accept();
                System.out.println("New client connected: "+clientSocket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket, bank);

                new Thread(clientHandler).start();
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}

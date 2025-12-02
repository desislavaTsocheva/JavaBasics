package task4_2;

import task4_1.Book;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class LibraryServer {
    private final int PORT;
    private final Library library;

    public LibraryServer(int port, Library library) {
        PORT = port;
        this.library = library;
    }

    public void start() {
        System.out.println("Starting Library Server on port: "+PORT);

        try{
            ServerSocket serverSocket=new ServerSocket(PORT);
            while(true){
                Socket clientSocket=serverSocket.accept();
                ClientServer clientServer=new ClientServer(clientSocket,library);
                Thread thread=new Thread(clientServer);
                thread.start();
                System.out.println("Client connected");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        //finally???
    }

    public static void main(String[] args) {
        int port=5000;
        LibraryServer server=new LibraryServer(port,new Library());
        server.start();
    }
}

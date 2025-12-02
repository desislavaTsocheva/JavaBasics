package task4_1;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class LibraryServer {
    private static final int PORT=5000;
    private static ArrayList<Book> books=new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("Starting Library Server on port: "+PORT);

        try{
            ServerSocket serverSocket=new ServerSocket(PORT);
            while(true){
                Socket clientSocket=serverSocket.accept();
                System.out.println("Client connected");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


}

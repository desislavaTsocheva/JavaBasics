package CashLogic;

import Enums.Commands;
import Enums.Data;
import Enums.Status;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ServerThread implements Runnable {
    private static ArrayList<Account> accounts;
    private HashMap<Data, Object> sessionData;
    private Socket socket;
    private Scanner reader;
    private Scanner scanner;
    private PrintStream writer;

    public ServerThread(Socket socket) throws IOException {
        accounts = new ArrayList<Account>(){
            {
                add(new Account(100, "123456789", 1234));
                add(new Account(1000, "987654321", 10000));
                add(new Account(8000, "111111111", 1000));
            }};
        sessionData = new HashMap<Data,Object>();
        socket = socket;
        reader = new Scanner(System.in);
        writer = System.out;
    }
    @Override
    public void run() {
        try{
            var out=socket.getOutputStream();
            var in=socket.getInputStream();

            scanner=new Scanner(System.in);

            reader=new Scanner(in);
            writer=new PrintStream(out);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        ServerLogic();

        try{
            reader.close();
            writer.close();
            socket.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void sendMessage(Commands commands, String message) {
       var thread=Thread.currentThread();
        System.out.println(thread.getId()+" "+commands+": "+message);
        writer.printf("%s: %s\n", commands, message);
    }

    public void sendStatus(Commands commands, Status status) {
        var thread=Thread.currentThread();
        System.out.println(commands + ": " + status);
        writer.printf("%s: %s\n", commands, status);
    }

    public void processMessage(Commands commands, String message) {
        switch (commands) {
            case WELCOME:
                sendMessage(Commands.WELCOME, String.valueOf(Status.OK));
                break;
            default:
                sendStatus(Commands.ERROR,Status.ERROR);
                break;
        }
    }

    public void ServerLogic(){
        sendMessage(Commands.WELCOME,"Welcome to the ATM");
        sendMessage(Commands.ASK_ACCOUNT_NUMBER,"Please enter your acc number");
        sendMessage(Commands.ASK_PIN,"Please enter your PIN");
        sendMessage(Commands.GET_BALANCE,"Your balance");
        sendMessage(Commands.DEPOSIT,"Please enter the amount of deposit");
        sendMessage(Commands.WITHDRAW,"Please enter the amount of withdraw");
        sendMessage(Commands.ERROR,"Error");
        getMessage();
    }

    public void getMessage() {
        var thread=Thread.currentThread();
        String message=reader.nextLine();
        String[] command=message.split(":");
        Commands commands=Commands.valueOf(command[0]);
        String args=command[1];
        processMessage(commands,args);
    }


}

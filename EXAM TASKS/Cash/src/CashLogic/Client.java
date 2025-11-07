package CashLogic;

import Enums.Commands;
import Enums.Status;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.PublicKey;
import java.util.Scanner;

public class Client  {

    public static Socket socket;
    public static Scanner reader;
    public static PrintStream writer;
    public static Scanner scanner;

    public static void main(String[] args) throws IOException {
        socket=new Socket("127.0.0.1",8080);
        var out=socket.getOutputStream();
        var in=socket.getInputStream();
        reader = new Scanner(in);
        writer=new PrintStream(out);
        scanner=new Scanner(in);
        showMenuAndSendChoice();
        RunLogic();


        reader.close();
        writer.close();
        socket.close();
    }

    public static void PerformAction(String message ){
        String[] cmd=message.split(":");
        Commands command=Commands.valueOf(cmd[0]);
        String args=cmd[1];
        var thread=Thread.currentThread();
        System.out.println(thread.getId()+" "+thread.getName()+" "+command+": "+args);
        switch(command){
            case WELCOME:
                sendMessage(Commands.WELCOME,"Welcome from the client");
                break;
            default:
                sendMessage(Commands.ERROR,"Unknown command");
                break;
        }
    }
    public static void sendMessage(Commands command, String message)
    {
        var thread = Thread.currentThread();
        System.out.println("(S" + thread.getId() + ")[" + command + "] " + message);
        writer.printf("%s:%s\n", command, message);
        String wholeMessage = reader.nextLine();
        System.out.println("(R" + thread.getId() + ")[" + command + "] " + wholeMessage.split(":")[1]);
    }
    public static void SendStatus(Commands command, Status status)
    {
        var thread = Thread.currentThread();
        System.out.println("(S" + thread.getId() + ")[" + command + "] " + status);
        writer.printf("%s:%s\n", command, status);
    }

    public static void RunLogic()
    {
        String wholeMessage = null;

        do {
            wholeMessage = reader.nextLine();
            PerformAction(wholeMessage);
        } while (wholeMessage != null && !wholeMessage.isEmpty() && !wholeMessage.isBlank());
    }

    private static void showMenuAndSendChoice() {
        System.out.println("Изберете действие:");
        System.out.println("1 - Теглене");
        System.out.println("2 - Депозит");
        System.out.println("3 - Проверка на баланс");
        System.out.println("4 - Изход");
        System.out.print("Ваш избор: ");
        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1":
                System.out.print("Сума за теглене: ");
                String w = scanner.nextLine().trim();
                sendMessage(Commands.WITHDRAW, w);
                break;
            case "2":
                System.out.print("Сума за депозит: ");
                String d = scanner.nextLine().trim();
                sendMessage(Commands.DEPOSIT, d);
                break;
            case "3":
                sendMessage(Commands.GET_BALANCE, "");
                break;
            case "4":
                sendMessage(Commands.EXIT, "");
                try {
                    socket.close();
                } catch (IOException ignored) {}
                break;
            default:
                System.out.println("Невалиден избор.");
                showMenuAndSendChoice();
                break;
        }
    }

}

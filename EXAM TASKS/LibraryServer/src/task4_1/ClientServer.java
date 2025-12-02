package task4_1;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientServer {
    private static ArrayList<Book> books=new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("127.0.0.1",5000);

        try {
            int choice;
            do {
                System.out.println("---------------------MENU----------------------");
                System.out.println("1-> ADD BOOK");
                System.out.println("2-> RENT BOOK");
                System.out.println("3-> RETURN BOOK");
                System.out.println("4-> EXIT");

                Scanner scanner = new Scanner(System.in);
                Scanner scanner1 = new Scanner(socket.getInputStream());

                PrintStream printStream = new PrintStream(socket.getOutputStream());

                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addBook();
                        break;
                    case 2:
                        rentBook();
                        break;
                    case 3:
                        returnBook();
                        break;
                }
            }
            while (choice != 4);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            if (socket != null)
                socket.close();
            if (socket != null)
                socket.close();
            if (socket != null)
                socket.close();
            }
        }

    public static Book findBook(int id){
        for(Book book:books){
            if(book.getId()==id){
                return book;
            }
        }
        return null;
    }

    public static void addBook(){
        String title;
        String author;

        System.out.print("Enter title: ");
        Scanner scanner=new Scanner(System.in);
        title=scanner.nextLine();

        System.out.print("Enter author: ");
        author=scanner.nextLine();

        if(title.isBlank()) {
            System.out.print("title is blank");
        }

        if(author.isBlank()) {
            System.out.print("author is blank");
        }

        Book book=new Book(title,author);
        books.add(book);
        System.out.println("Success " + book);
    }

    public static void rentBook(){
        int id;
        String name;

        System.out.print("Enter id: ");
        Scanner scanner=new Scanner(System.in);
        id=scanner.nextInt();

        Scanner scanner1=new Scanner(System.in);
        System.out.print("Enter user: ");
        name=scanner1.nextLine();
        Book book=findBook(id);

        if(findBook(id)==null){
            System.out.println("Book not found");
        }

        if(name.isBlank()){
            System.out.println("name is blank");
        }

        assert book != null;
        if(book.isRented()){
            System.out.println("book is rented");
        }

        System.out.println("This book was rented successfully "+book);
    }

    public static void returnBook(){
        int id;
        System.out.print("Enter id: ");
        Scanner scanner=new Scanner(System.in);
        id=scanner.nextInt();
        scanner.nextLine();

        Book book=findBook(id);
        if(book==null){
            System.out.println("Book not found");
        }
        if(book.isRented()){
            System.out.print("book is rented");
        }
        else{
            System.out.println("That book was returned successfully "+book);
        }
        book.returnBook();
    }
}

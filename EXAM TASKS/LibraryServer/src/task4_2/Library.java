package task4_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    private ArrayList<Book> books=new ArrayList<>();
    public synchronized void addBook(){
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

    public synchronized void rentBook(){
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

    public synchronized void returnBook(){
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

    public synchronized Book findBook(int id){
        for(Book book:books){
            if(book.getId()==id){
                return book;
            }
        }
        return null;
    }

    public synchronized List<Book> getBooks(){
        return books;
    }
}

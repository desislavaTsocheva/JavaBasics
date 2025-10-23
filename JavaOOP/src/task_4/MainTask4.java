package task_4;

import java.util.ArrayList;

public class MainTask4 {
    public static void main(String[] args) {
        ArrayList<LibraryItem> items = new ArrayList<>();
        Book book = new Book();
        book.setAuthor("Ivan Vazov");
        items.add(book);

        Magazine magazine = new Magazine();
        magazine.setIssueNumber("AE28974");
        items.add(magazine);

        for(LibraryItem item : items) {
            item.displayInfo();
        }
    }
}

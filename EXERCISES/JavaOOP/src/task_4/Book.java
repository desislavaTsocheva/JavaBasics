package task_4;

public class Book extends LibraryItem {
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public void displayInfo() {
        System.out.println("The author is: " + author);
    }
}

package task4_1;

public class Book {
    private final int id;
    private static int count = 1;
    private String title;
    private String author;
    private boolean rented;
    private String rentedBy;

    public Book(String title, String author) {
        this.id = count++;
        this.title = title;
        this.author = author;
        this.rented = false;
        this.rentedBy = null;
    }

    public int getId() {
        return id;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public void setRentedBy(String rentedBy) {
        if (!rented) {
            this.rentedBy = rentedBy;
            this.rented = true;
        }
    }

    public void returnBook(){
        this.rented = false;
        this.rentedBy = null;
    }

    @Override
    public String toString() {
        if(rented) {
            return "Book [id=" + id + ", title=" + title + ", author=" + author + ", rented=";
        }
        else{
            return "Book [id=" + id + ", title=" + title + ", author=" + author;
        }
    }
}

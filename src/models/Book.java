package models;

public class Book {
    private int bookId; // Bu alan veritabanındaki 'book_id' ile eşleşiyor
    private String title;
    private String author;
    private String category;
    private String status;

    // Constructor
    public Book(int bookId, String title, String author, String category, String status) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = status;
    }

    // Getter ve Setter metodları
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
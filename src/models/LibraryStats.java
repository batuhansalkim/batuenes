package models;

public class LibraryStats {
    private int studentCount;
    private int adminCount;
    private int totalBooks;
    private int lostBooks;
    private int borrowedBooks;
    private int booksOnShelf;

    public LibraryStats(int studentCount, int adminCount, int totalBooks, int lostBooks, int borrowedBooks, int booksOnShelf) {
        this.studentCount = studentCount;
        this.adminCount = adminCount;
        this.totalBooks = totalBooks;
        this.lostBooks = lostBooks;
        this.borrowedBooks = borrowedBooks;
        this.booksOnShelf = booksOnShelf;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public int getAdminCount() {
        return adminCount;
    }

    public int getTotalBooks() {
        return totalBooks;
    }

    public int getLostBooks() {
        return lostBooks;
    }

    public int getBorrowedBooks() {
        return borrowedBooks;
    }

    public int getBooksOnShelf() {
        return booksOnShelf;
    }
}

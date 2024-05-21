package books;

import java.time.LocalDate;

public class Book {
    private String title;
    private String author;
    private String bookId;
    private String category;
    private int stock;
    private LocalDate duration;

    public Book (String bookId, String title, String author, String category, int stock) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.stock = stock;
    }

    public void borrowBook(LocalDate borrowDate) {
        this.duration = borrowDate.plusDays(7);
    }

    public LocalDate getDuration() {
        return duration;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor (String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setBookId (String bookId) {
        this.bookId = bookId;
    }
    public String getBookId() {
        return bookId;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategory() {
        return category;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public int getStock() {
        return stock;
    }

    public void displayBookInfo() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Stock: " + stock);
        System.out.println("Category: " + category);
        System.out.println("--------------------------");
    }
}

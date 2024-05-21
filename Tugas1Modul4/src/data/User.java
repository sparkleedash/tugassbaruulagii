package data;

import books.Book;

import java.util.ArrayList;

public class User {
    ArrayList<Book> bookList = new ArrayList<>();

    public void displayBook() {
        System.out.println("Daftar Buku:");
        for (Book book : bookList) {
            System.out.println(book.getTitle());
        }
    }

    public void addBook(Book newBook) {
        bookList.add(newBook);
        System.out.println("Buku berhasil ditambahkan: " + newBook.getTitle());
    }

    public Book[] getBookList() {
        return bookList.toArray(new Book[0]);
    }
}

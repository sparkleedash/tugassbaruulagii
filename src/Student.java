import java.util.ArrayList;

public class Student {
    String name;
    String faculty;
    String programStudi;
    String nim;
    ArrayList<String> borrowedBooks = new ArrayList<>();

    static void logout() {
        System.out.println("System Logout...");
    }

    static void displayBooks(ArrayList<ArrayList<String>> bookList) {
        System.out.println("==== Available Books ====");
        System.out.println("==============================================================================");
        System.out.println("|| ID || Title                                  || Author          || Stock ||");
        System.out.println("==============================================================================");
        for (int i = 0; i < bookList.get(0).size(); i++) {
            String id = bookList.get(2).get(i);
            String title = bookList.get(3).get(i);
            String author = bookList.get(1).get(i);
            String stock = bookList.get(0).get(i);

            System.out.printf("|| %-2s || %-38s || %-15s || %-5s ||%n", id, title, author, stock);
        }
        System.out.println("==============================================================================");
    }
    void displayBorrowedBooks(ArrayList<ArrayList<String>> bookList) {
        System.out.println("==== Books Borrowed by " + name + " ====");
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books borrowed.");
        } else {
            for (String bookId : borrowedBooks) {
                int index = Integer.parseInt(bookId) - 1;
                String title = bookList.get(3).get(index);
                String author = bookList.get(1).get(index);
                System.out.println("Title: " + title + ", Author: " + author);
            }
        }
    }
}


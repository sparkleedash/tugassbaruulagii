import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User {
    String name;
    String faculty;
    String programStudi;
    String nim;
    ArrayList<String> borrowedBooks = new ArrayList<>();

    static void returnBooks(Student student) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==== Return Books ====");
        if (student.borrowedBooks.isEmpty()) {
            System.out.println("Belum ada buku yang terpinjam");
            System.out.println("Silahkan pilih buku terlebih dahulu");
            return;
        }

        System.out.println("Books borrowed by " + student.name + ":");
        for (String bookId : student.borrowedBooks) {

            boolean found = false;
            for (Book book : bookList) {
                if (book.getBookId().equals(bookId)) {
                    found = true;
                    int stock = book.getStock();
                    stock++;
                    book.setStock(stock);
                    break;
                }
            }
            if (!found) {
                System.out.println("Book with ID " + bookId + " not found.");
            }
        }

        student.borrowedBooks.clear();

        System.out.println("All books returned successfully.");
    }

    static void logout() {
        System.out.println("System Logout...");
    }

    static void displayInfo(Student student) {
        System.out.println("==== Student Information ====");
        System.out.println("Name: " + student.name);
        System.out.println("NIM: " + student.nim);
        System.out.println("Faculty: " + student.faculty);
        System.out.println("Program Study: " + student.programStudi);
    }

    void displayBooks(){
        super.displayBooks(bookList);
    }

    void showBorrowedBooks(ArrayList<Book> bookList) {
        System.out.println("==== Books Borrowed by " + name + " ====");
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books borrowed.");
        } else {
            System.out.println("============================================================");
            System.out.printf("| %-25s | %-20s |%n", "Title", "Author");
            System.out.println("============================================================");
            for (String bookId : borrowedBooks) {
                int index = Integer.parseInt(bookId) - 1;
                Book book = bookList.get(index);
                String title = book.getTitle();
                String author = book.getAuthor();
                System.out.printf("| %-25s | %-20s |%n", title, author);
            }
            System.out.println("============================================================");
        }
    }
}

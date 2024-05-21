package data;

import com.main.Main;
import util.iMenu;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import books.Book;

public class Student extends User implements iMenu {
    private String name;
    private String faculty;
    private String nim;
    private String programStudi;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();
    Admin admin = Admin.getInstance();
    Scanner scanner = new Scanner(System.in);

    public Student(Admin admin) {
        this.admin = admin;
    }

    public Student() {
        this.name = this.name;
        this.faculty = this.faculty;
        this.nim = this.nim;
        this.programStudi = this.programStudi;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }

    public void setProgramStudi(String progStudi) {
        this.programStudi = progStudi;
    }

    public String getMajor() {
        return programStudi;
    }

    public void displayInfo() {
        System.out.println("Nama: " + name);
        System.out.println("Fakultas: " + faculty);
        System.out.println("NIM: " + nim);
        System.out.println("Program Studi: " + programStudi);
    }

    @Override
    public void menu(ArrayList<Student> studentList) {
        boolean does = true;

        while (does) {
            try {
                System.out.println("==== Student Menu ====");
                System.out.println("1. Tampilkan Informasi");
                System.out.println("2. Buku Terpinjam");
                System.out.println("3. Pilih Buku");
                System.out.println("4. Kembalikan Buku");
                System.out.println("5. Log Out");
                System.out.print("Choose option (1-5): ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        if (!studentList.isEmpty()) {
                            studentList.get(0).displayInfo();
                        } else {
                            System.out.println("Belum ada data mahasiswa yang tersedia.");
                        }
                        break;
                    case 2:
                        showBorrowedBooks();
                        break;
                    case 3:
                        choiceBook();
                        break;
                    case 4:
                        returnBook();
                        break;
                    case 5:
                        Main main = new Main();
                        logout(main);
                        does = false;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }

                boolean inputValid = false;
                while (!inputValid) {
                    System.out.print("Apakah Anda ingin melanjutkan program? (y/n): ");
                    String lanjutkan = scanner.next();
                    if (lanjutkan.equalsIgnoreCase("y")) {
                        inputValid = true;
                    } else if (lanjutkan.equalsIgnoreCase("n")) {
                        does = false;
                        System.out.println("TERIMA KASIH SUDAH MENGGUNAKAN PROGRAM INI");
                        inputValid = true;
                    } else {
                        System.out.println("Masukkan inputan yang valid!");
                    }
                }
            } catch (InputMismatchException e) {
                System.err.println("Input tidak valid. Harap masukkan angka.");
                scanner.nextLine();
            }
        }
    }


    public void logout(Main main) {
        System.out.println("Logging out...");
        main.menu();
    }

    public void returnBook() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Belum ada buku terpinjam.");
            return;
        }

        System.out.println("Buku terpinjam:");
        for (int i = 0; i < borrowedBooks.size(); i++) {
            System.out.println((i + 1) + ". " + borrowedBooks.get(i).getTitle());
        }

        System.out.print("Masukkan ID buku yang akan Anda kembalikan: ");
        String bookId = scanner.next();

        boolean bookFound = false;
        for (int i = 0; i < borrowedBooks.size(); i++) {
            if (borrowedBooks.get(i).getBookId().equals(bookId)) {
                bookFound = true;
                Book returnedBook = borrowedBooks.remove(i);

                LocalDate returnDate = LocalDate.now();
                LocalDate dueDate = returnedBook.getDuration();
                if (returnDate.isAfter(dueDate)) {
                    long daysLate = returnDate.until(dueDate, ChronoUnit.DAYS);
                    System.out.println("Anda terlambat mengembalikan buku " + returnedBook.getTitle() +
                            ". Anda telat " + daysLate + " hari.");
                } else {
                    System.out.println("Anda telah berhasil mengembalikan buku: " + returnedBook.getTitle());
                }

                returnedBook.setStock(returnedBook.getStock() + 1);
                break;
            }
        }

        if (!bookFound) {
            System.out.println("Buku dengan ID " + bookId + " tidak ditemukan");
        }
    }


    public void showBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Belum ada buku terpinjam");
            return;
        }

        System.out.println("Buku terpinjam:");
        for (int i = 0; i < borrowedBooks.size(); i++) {
            Book book = borrowedBooks.get(i);
            System.out.println((i + 1) + ". " + book.getTitle() + " (ID: " + book.getBookId() + ")");
        }
    }

    public void choiceBook() {
        Book[] bookList = admin.getBookList();
        if (bookList != null) {
            admin.displayBook();
            System.out.println("Apakah Anda ingin meminjam buku di atas? (y/n)");
            String answer = scanner.next();

            if (answer.equalsIgnoreCase("y")) {
                System.out.print("Masukkan ID buku yang ingin Anda pinjam: ");
                String bookId = scanner.next();

                boolean bookFound = false;
                for (Book book : bookList) {
                    if (book != null && book.getBookId().equals(bookId)) {
                        bookFound = true;
                        if (book.getStock() > 0) {
                            System.out.println("Buku berhasil dipinjam: " + book.getTitle());
                            book.borrowBook(LocalDate.now().plusDays(7));
                            book.setStock(book.getStock() - 1);
                            borrowedBooks.add(book);
                        } else {
                            System.out.println("Maaf, stok buku " + book.getTitle() + " habis.");
                        }
                        break;
                    }
                }

                if (!bookFound) {
                    System.out.println("Buku dengan ID " + bookId + " tidak ditemukan.");
                }
            }
        } else {
            System.out.println("Daftar buku kosong.");
        }
    }
}
package data;

import com.main.Main;
import exception.custom.IllegalAdminAccess;
import util.iMenu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;
import books.Book;
import books.HistoryBook;
import books.TextBook;
import books.StoryBook;

public class Admin extends User implements iMenu {
    private static String adminUsername = "admin";
    private static String adminPassword = "admin";
    public ArrayList<Student> studentList = new ArrayList<>();
    private static Admin instance = null;
    public ArrayList<Book> bookList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    private Admin() {}

    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }


    @Override
    public void menu(ArrayList<Student> studentList) {
        while (true) {
            try {
                System.out.println("======DASHBOARD ADMIN======");
                System.out.println("1. Add Student");
                System.out.println("2. Add Book");
                System.out.println("3. Display Book");
                System.out.println("4. Display Students");
                System.out.println("5. Kembali ke menu awal");
                System.out.print("Pilihan anda: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        inputBook();
                        break;
                    case 3:
                        displayBook();
                        break;
                    case 4:
                        displayStudents();
                        break;
                    case 5:
                        System.out.println("TERIMAKASIH SUDAH MENGGUNAKAN PROGRAM INI");
                        Main main = new Main();
                        logout(main);
                        return;
                    default:
                        System.err.println("Pilihan tidak valid.");
                }

                boolean inputValid = false;
                while (!inputValid) {
                    System.out.print("Apakah Anda ingin melanjutkan program? (y/n): ");
                    String lanjutkan = scanner.next();
                    if (lanjutkan.equalsIgnoreCase("y")) {
                        inputValid = true;
                    } else if (lanjutkan.equalsIgnoreCase("n")) {
                        System.out.println("TERIMA KASIH SUDAH MENGGUNAKAN PROGRAM INI");
                        return;
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

    public boolean menuAdmin() throws IllegalAdminAccess {
        String inputUser;
        String inputPass;

        boolean isAdminLoggedIn = false;

        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> studentList = new ArrayList<>();

        do {
            System.out.print("Username: ");
            inputUser = scanner.next();
            System.out.print("Password: ");
            inputPass = scanner.next();
            isAdminLoggedIn = Admin.isAdmin(inputUser, inputPass);

            if (isAdminLoggedIn) {

                Admin admin = Admin.getInstance();
                admin.menu(studentList);
            }
        } while (!isAdminLoggedIn);

        scanner.close();
        return isAdminLoggedIn;
    }


    public boolean menuStudent() {
        System.out.println("====== LOGIN AS STUDENT ======");
        System.out.print("Enter NIM: ");
        String nim = scanner.next();

        for (Student student : studentList) {
            if (student.getNim().equals(nim)) {
                System.out.println("Login successful!");
                student.menu(studentList);
                return true;
            }
        }

        System.out.println("Login failed. NIM not found.");
        return false;
    }


    public static boolean isAdmin(String username, String password) {
        return username.equals(adminUsername) && password.equals(adminPassword);
    }

    String generateId() {
        String uniqueID = UUID.randomUUID().toString();
        String id = uniqueID.replaceAll("-", "").toLowerCase();
        return String.format("%s-%s-%s", id.substring(0, 4), id.substring(4, 8), id.substring(8, 12));
    }

    public void addStudent() {
        scanner.nextLine();
        System.out.print("Nama:");
        String name = scanner.nextLine();

        String nim;

        do {
            System.out.print("NIM: ");
            nim = scanner.nextLine();

            if (nim.length() != 15) {
                System.out.println("Inputan NIM tidak valid, NIM harus memiliki panjang 15 digit!");
            }
        } while (nim.length() != 15);

        System.out.print("Fakultas: ");
        String faculty = scanner.nextLine();

        System.out.print("Program Studi: ");
        String programStudi = scanner.nextLine();

        Student newStudent = new Student();
        newStudent.setName(name);
        newStudent.setNim(nim);
        newStudent.setFaculty(faculty);
        newStudent.setProgramStudi(programStudi);

        studentList.add(newStudent);
        System.out.println("Data Added!");
    }

    public void displayStudents() {
        System.out.println("=======================================================================================");
        System.out.printf("| %-20s | %-15s | %-20s | %-20s |\n", "Nama", "NIM", "Fakultas", "Program Studi");
        System.out.println("=======================================================================================");
        for (Student student : studentList) {
            System.out.printf("| %-20s | %-15s | %-20s | %-20s |\n",
                    student.getName(),
                    student.getNim(),
                    student.getFaculty(),
                    student.getMajor());
        }
        System.out.println("========================================================================================");
    }

    public void inputBook() {
        System.out.println("Masukan kategori buku yang ingin ditambahkan : ");
        System.out.println("1. History Book ");
        System.out.println("2. Story Book");
        System.out.println("3. Text Book");
        System.out.print("Pilihan anda: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        String id = generateId();

        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();

        Book newBook;

        switch (option){
            case 1:
                newBook = new HistoryBook(id, title, author, stock, "History");
                break;
            case 2:
                newBook = new StoryBook(id, title, author, stock, "Story");
                break;
            case 3:
                newBook = new TextBook(id, title, author, stock, "Text");
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                return;
        }

        bookList.add(newBook);
    }

    public void displayBook() {
        System.out.println("==============================================================================================================");
        System.out.printf("| %-15s | %-30s | %-20s | %-20s | %-10s |\n", "ID", "Judul", "Penulis", "Kategori", "Stok");
        System.out.println("==============================================================================================================");
        for (Book book : bookList) {
            System.out.printf("| %-15s | %-30s | %-20s | %-20s | %-10d |\n",
                    book.getBookId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getCategory(),
                    book.getStock());
        }
        System.out.println("==============================================================================================================");
    }

    public Book[] getBookList() {
        return bookList.toArray(new Book[0]);
    }
}

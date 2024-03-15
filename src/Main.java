import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<ArrayList<String>> bookList = new ArrayList<>();
    static ArrayList<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        BookList();
        StudentList();
        menu();
    }

    static void BookList() {
        ArrayList<String> stokBuku = new ArrayList<>();
        ArrayList<String> author = new ArrayList<>();
        ArrayList<String> idBuku = new ArrayList<>();
        ArrayList<String> judul = new ArrayList<>();

        stokBuku.add("10");
        author.add("Nancy Springer");
        idBuku.add("1");
        judul.add("The Left-Handed Lady");

        stokBuku.add("5");
        author.add("Nancy Springer");
        idBuku.add("2");
        judul.add("The Gypsy Goodbye");

        bookList.add(stokBuku);
        bookList.add(author);
        bookList.add(idBuku);
        bookList.add(judul);
    }

    static void StudentList() {
        Student student1 = new Student();
        student1.name = "Safwa Nusaibah";
        student1.nim = "202310370311002";
        student1.faculty = "Teknik";
        student1.programStudi = "Informatika";

        Student student2 = new Student();
        student2.name = "Safira Babsail";
        student2.nim = "202310370311512";
        student2.faculty = "Teknik";
        student2.programStudi = "Elektro";

        studentList.add(student1);
        studentList.add(student2);
    }

    static void menu() {
        System.out.println("==== Library System ====");
        System.out.println("1. Login as Student");
        System.out.println("2. Login as Admin");
        System.out.println("3. Exit");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose option (1-3): ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                Student loggedInStudent = loginStudent();
                if (loggedInStudent != null) {
                    menuStudent(loggedInStudent);
                } else {
                    System.out.println("Invalid credentials for students.");
                    menu();
                }
                break;
            case 2:
                System.out.println("==== Admin Menu ====");

                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                scanner.nextLine();

                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                if (!username.equals("admin") && !password.equals("admin")) {
                    System.out.println("Invalid credentials for admin.\n");
                    menu();
                }
                else {
                    menuAdmin();
                }
                break;
            case 3:
                System.out.println("Thank you. Exiting program.");
                break;
            default:
                System.out.println("Invalid option. Please choose again.");
                menu();
        }
    }

    static Student loginStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your NIM (input 99 untuk back): ");
        String nim = scanner.nextLine();

        if(nim.equals("99")){
            menu();
        }

        if (nim.length() != 15 || !nim.matches("\\d+")) {
            System.out.println("Nim harus 15 digit.");
            loginStudent();
        }

        for (Student student : studentList) {
            if (student.nim.equals(nim)) {
                return student;
            }
        }
        return null;
    }


    static void menuStudent(Student student) {
        System.out.println("==== Student Menu ====");
        System.out.println("1. Buku Terpinjam");
        System.out.println("2. Pinjam Buku");
        System.out.println("3. Logout");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose option (1-3): ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                student.displayBorrowedBooks(bookList);
                menuStudent(student);
                break;
            case 2:
                Student.displayBooks(bookList);
                System.out.println("Enter the ID of the book you want to borrow (enter 99 to go back):");
                int bookId = scanner.nextInt();

                if (bookId == 99) {
                    menuStudent(student);
                    return;
                }

                borrowBook(bookId, student);
                menuStudent(student);
                break;
            case 3:
                menu();
                break;
            default:
                System.out.println("Invalid option. Please choose again.");
                menuStudent(student);
        }
    }

    static void borrowBook(int bookId, Student student) {
        int index = bookId - 1;
        if (index >= 0 && index < bookList.get(0).size()) {
            int stock = Integer.parseInt(bookList.get(0).get(index));
            if (stock > 0) {
                stock--;
                bookList.get(0).set(index, String.valueOf(stock));
                student.borrowedBooks.add(String.valueOf(bookId));
                System.out.println("Book borrowed successfully.");
            } else {
                System.out.println("Sorry, the book is out of stock.");
            }
        } else {
            System.out.println("Invalid book ID.");
        }
    }

    static void menuAdmin() {
        System.out.println("==== Admin Menu ====");
        System.out.println("1. Add Student");
        System.out.println("2. Display registered Students");
        System.out.println("3. Logout");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose Option (1-3): ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                Admin.addStudent(studentList);
                menuAdmin();
                break;
            case 2:
                Admin.displayStudent(studentList);
                menuAdmin();
                break;
            case 3:
                menu();
                break;
            default:
                System.out.println("Invalid option. Please choose again.");
                menuAdmin();
        }
    }
}

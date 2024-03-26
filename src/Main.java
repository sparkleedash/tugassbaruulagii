import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Book> bookList = new ArrayList<>();
    static ArrayList<Student> studentList = new ArrayList<>();

    static String choice = "";

    public static void main(String[] args) {
        BookList();
        StudentList();
        menu();
    }


    static void BookList() {

        Book book1 = new Book(Admin.generateId(), "The Left-Handed Lady", "Nancy Springer", "10");
        Book book2 = new Book(Admin.generateId(), "The Gypsy Goodbye", "Nancy Springer", "5");

        bookList.add(book1);
        bookList.add(book2);
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
        scanner.nextLine();

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

                Admin admin = new Admin();

                System.out.print("Enter username: ");
                admin.adminUsername= scanner.nextLine();

                System.out.print("Enter password: ");
                admin.adminPassword = scanner.nextLine();

                if (admin.isAdmin()) {
                    menuAdmin();
                }
                else {
                    menu();
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
        //System.out.println("Logged in as " + student.name);
        System.out.println("==== Student Menu " + student.name + " ====");
        System.out.println("1. Buku Terpinjam");
        System.out.println("2. Pinjam Buku");
        System.out.println("3. Logout");
        System.out.println("4. Kembalikan Buku");
        System.out.println("5. Student info");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose option (1-3): ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                student.showBorrowedBooks(bookList);
                menuStudent(student);
                break;
            case 2:
                do {

                    Student.displayBooks(bookList);
                    System.out.println("Enter the ID of the book you want to borrow (enter 99 to go back):");
                    int bookId = scanner.nextInt();

                    if (bookId == 99) {
                        menuStudent(student);
                        return;
                    }

                    User.addBook(bookId, student, bookList);


                    System.out.println("Berapa Lama buku akan dipinjam? (Maksimal 14 hari)");
                    System.out.print("Input lama (Hari): ");
                    int hari = scanner.nextInt();

                    scanner.nextLine();

                    System.out.println("Do you want to borrow anpther book? (Y/N):");
                    choice = scanner.nextLine();
                }while(!choice.equals("N") && !choice.equals("n"));
                menuStudent(student);
                break;
            case 3:
                menu();
                break;
            case 4:
                Student.returnBooks(student);
                menuStudent(student);
                break;
            case 5:
                Student.displayInfo(student);
                menuStudent(student);
            default:
                System.out.println("Invalid option. Please choose again.");
                menuStudent(student);
        }
    }



    static void menuAdmin() {
        System.out.println("==== Admin Menu ====");
        System.out.println("1. Add Student");
        System.out.println("2. Display registered Students");
        System.out.println("3. Logout");
        System.out.println("4. input book");

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
            case 4:
                Admin.inputBook();
                menuAdmin();
                break;
            default:
                System.out.println("Invalid option. Please choose again.");
                menuAdmin();
        }
    }
}

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Admin {
    String adminUsername;
    String adminPassword;
    static ArrayList<Student> studentList = new ArrayList<>();
    static ArrayList<Book> bookList = new ArrayList<>();

    static void inputBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select book category:");
        System.out.println("1. Story book");
        System.out.println("2. History book");
        System.out.println("3. Text book");
        System.out.print("Choose category(1-3): ");
        int category = scanner.nextInt();
        scanner.nextLine();


        System.out.println("==== Input New Book ====");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter stock: ");
        int stock = scanner.nextInt();

        String newBookId = Admin.generateId();


        Book newBook = new Book(newBookId, title, author, String.valueOf(stock));
        bookList.add(newBook);

        System.out.println("New book added successfully.");
    }

    boolean isAdmin(){
        if (adminUsername.equals("admin") && adminPassword.equals("admin")) {
            return true;
        }
        else {
            return false;
        }
    }

    static void displayStudent(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students registered.");
            return;
        }
        System.out.println("==== Registered Students ====");
        for (Student student : students) {
            System.out.println("Name: " + student.name);
            System.out.println("Faculty: " + student.faculty);
            System.out.println("NIM: " + student.nim);
            System.out.println("Program: " + student.programStudi);
            System.out.println("-----------------------------");
        }
    }

    static void addStudent(ArrayList<Student> students) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==== Add Student ====");
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student NIM: ");
        String nim = scanner.nextLine();
        if (nim.length() != 15 || !nim.matches("\\d+")) {
            System.out.println("Nim harus 15 digit");
            return;
        }
        System.out.print("Enter student faculty: ");
        String faculty = scanner.nextLine();
        System.out.print("Enter student program: ");
        String programStudi = scanner.nextLine();

        Student newStudent = new Student();
        newStudent.name = name;
        newStudent.nim = nim;
        newStudent.faculty = faculty;
        newStudent.programStudi = programStudi;

        students.add(newStudent);

        System.out.println("Student added successfully.");
    }

    static String generateId() {
        String id1 = UUID.randomUUID().toString().substring(0, 4);
        String id2 = UUID.randomUUID().toString().substring(0, 4);
        String id3 = UUID.randomUUID().toString().substring(0, 4);

        String id = id1 + "-" + id2 + "-" + id3;
        return id;
    }


}


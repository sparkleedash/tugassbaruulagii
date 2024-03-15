import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
    String adminUsername;
    String adminPassword;

    static void displayStudent(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students registered.");
            return;
        }
        System.out.println("==== Registered Students ====");
        for (Student student : students) {
            System.out.println("Name: " + student.name);
            System.out.println("NIM: " + student.nim);
            System.out.println("Faculty: " + student.faculty);
            System.out.println("Program Studi: " + student.programStudi);
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
}


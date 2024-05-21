package com.main;

import data.Student;
import data.Admin;
import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);
    Admin admin = Admin.getInstance();

    public void menu() {
        try {
            System.out.println("==== Library System ====");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");

            System.out.print("Choose option (1-3): ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    admin.menuStudent();
                    break;
                case 2:
                    admin.menuAdmin();
                    break;
                case 3:
                    System.out.println("Thank you. Exiting program.");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
            menu();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String inputNIM() {
        System.out.print("Enter NIM: ");
        return scanner.next();
    }

    public Object[] checkNIM(String nim) {
        boolean found = false;
        Student foundStudent = null;

        for (Student student : admin.studentList) {
            if (student.getNim().equals(nim)) {
                found = true;
                foundStudent = student;
                break;
            }
        }

        return new Object[]{found, foundStudent};
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }
}

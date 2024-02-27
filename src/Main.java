import java.util.Scanner;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        option();
    }

    static void option() {

        String main = "<html>===== Library System =====<br>1. Login as Student<br>2. Login as Admin<br>3. Exit</html>";

        JOptionPane.showMessageDialog(null, main);
        int option = Integer.parseInt((JOptionPane.showInputDialog("Choose option (1-3)")));

        /*System.out.println("===== Library System =====");
        System.out.println("1. Login as Student");
        System.out.println("2. Login as Admin");
        System.out.println("3. Exit");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose option (1-3): ");
        int option = scanner.nextInt();
        scanner.nextLine();*/

        switch (option) {
            case 1:
                option1();
                break;
            case 2:
                option2();
                break;
            case 3:
                JOptionPane.showMessageDialog(null,"adios");
                //System.out.println("adios");
                break;
        }
    }

    static void option1() {
        Scanner scanner = new Scanner(System.in);
        String NIM;

        //do {
        NIM = JOptionPane.showInputDialog("Enter your NIM : ");

        if (NIM.length() == 15 && NIM.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Successful login as Student");
        } else {
            JOptionPane.showMessageDialog(null, "User not found");
            option();
        }

        //} while (NIM.length() != 15 || !NIM.matches("\\d+"));

            /*System.out.print("Enter your NIM : ");
            NIM = scanner.nextLine();

            if (NIM.length() == 15 && NIM.matches("\\d+")) {
                System.out.println("Successful login as Student");
            } else {
                System.out.println("User not found");
            }
        } while (NIM.length() != 15 || !NIM.matches("\\d+"));
             */
    }

    static void option2() {
        Scanner scanner = new Scanner(System.in);
        String userName;
        String password;

        userName = JOptionPane.showInputDialog("Enter your username (admin): ");
        password = JOptionPane.showInputDialog("Enter your password (admin): ");

        if (userName.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
            JOptionPane.showMessageDialog(null, "Successful login as admin");
        } else {
            JOptionPane.showMessageDialog(null, "User not found");
            option();
        }

        /*do {
            System.out.print("Enter your username (admin): ");
            userName = scanner.nextLine();

            System.out.print("Enter your password (admin): ");
            password = scanner.nextLine();

            if (userName.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
                System.out.println("Successful login as admin");
            } else {
                System.out.println("User not found");
            }

        } while (!(userName.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")));*/
    }
}

//sesuaikan dengan modul
// GUI


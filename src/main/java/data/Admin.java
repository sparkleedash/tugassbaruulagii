package data;

import com.main.LibrarySystem;
import books.Book;
import util.iMenu;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ButtonType;
import java.util.Random;


public class Admin extends User implements iMenu {

    //=================================== ATRIBUT =====================================
    public static String adminusername = "admin";
    public static String adminpassword = "admin";

//=================================== Main & Start Method ==================================



//======================================= MENU Method =======================================
    @Override
    public void menu(){
        Stage adminMenuStage = new Stage();
        adminMenuStage.setTitle("Saff Library - Admin Menu");

        //Label
        Label sceneTitle = new Label("Menu Admin");

        //Font Style
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

        //Font Color
        sceneTitle.setStyle("-fx-text-fill: #FFC0CB;");

        //Button
        Button addStudentButton     = new Button("Tambah Mahasiswa");
        Button displayStudentButton = new Button("Daftar Mahasiswa");
        Button addBookButton        = new Button("Tambah Buku");
        Button displayBookButton    = new Button("Daftar Buku");
        Button logoutButton         = new Button("Logout");

        addStudentButton.setStyle("-fx-background-color: #FFC0CB;");
        displayStudentButton.setStyle("-fx-background-color: #FFC0CB;");
        addBookButton.setStyle("-fx-background-color: #FFC0CB;");
        displayBookButton.setStyle("-fx-background-color: #FFC0CB;");
        logoutButton.setStyle("-fx-background-color: #FFC0CB;");

        //Grid Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        grid.add(sceneTitle,0,1);

        grid.add(addStudentButton, 2,0);
        grid.add(displayStudentButton, 2,1);
        grid.add(addBookButton, 2,2);
        grid.add(displayBookButton, 2, 3);
        grid.add(logoutButton,2,4);

        grid.setVgap(10);
        grid.setHgap(6);

        Scene scene = new Scene(grid, 1360, 720);
        adminMenuStage.setScene(scene);
        adminMenuStage.show();

        //Action Button
        addStudentButton.setOnAction(event -> {
            addstudent();
        });

        displayStudentButton.setOnAction(event -> {
            displaystudent();
            adminMenuStage.close();
        });

        addBookButton.setOnAction(event -> {
            addBook();
            adminMenuStage.close();
        });

        displayBookButton.setOnAction(event -> {
            displayBook();
            adminMenuStage.close();
        });


        logoutButton.setOnAction(event -> {
            LibrarySystem librarySystemObj = new LibrarySystem();
            librarySystemObj.start(new Stage());
            adminMenuStage.close();
        });

    }

//===================================== Other Method =======================================
    public void addstudent() {

        // Membuat form baru
        Stage addStudentStage = new Stage();
        addStudentStage.setTitle("Tambah Mahasiswa");


        //Label
        Label sceneTitle    = new Label("Tambah Mahasiswa");
        Label nameLabel     = new Label("Nama");
        Label nimLabel      = new Label("NIM");
        Label fakultasLabel = new Label("Fakultas");
        Label jurusanLabel  = new Label("Jurusan");

        sceneTitle.setStyle("-fx-background-color: #FFC0CB;");
        nameLabel.setStyle("-fx-background-color: #FFC0CB;");
        nimLabel.setStyle("-fx-background-color: #FFC0CB;");
        fakultasLabel.setStyle("-fx-background-color: #FFC0CB;");
        jurusanLabel.setStyle("-fx-background-color: #FFC0CB;");

        //Notification Label
        Label sumbitFailed = new Label("NIM harus 15 digit!");
        sumbitFailed.setVisible(false);


        //Field
        TextField nameField     = new TextField();
        TextField nimField      = new TextField();
        TextField fakultasField = new TextField();
        TextField jurusanField  = new TextField();

        nameField.setStyle("-fx-background-color: #FFC0CB;");
        nimField.setStyle("-fx-background-color: #FFC0CB;");
        fakultasField.setStyle("-fx-background-color: #FFC0CB;");
        jurusanField.setStyle("-fx-background-color: #FFC0CB;");

        //Font Style
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        nameLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        nimLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        fakultasLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        jurusanLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));

        //Font Color
        sceneTitle.setStyle("-fx-text-fill: #FFC0CB;");
        sumbitFailed.setStyle("-fx-text-fill: #FFC0CB;");

        //Button
        Button submitButton = new Button("Submit");

        //Grid Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add(sceneTitle, 0,0);

        grid.add(nameLabel, 0,1);
        grid.add(nimLabel, 0,2);
        grid.add(fakultasLabel, 0,3);
        grid.add(jurusanLabel, 0,4);

        grid.add(nameField, 1,1);
        grid.add(nimField, 1,2);
        grid.add(fakultasField, 1,3);
        grid.add(jurusanField, 1,4);

        grid.add(submitButton,1,5);

        grid.add(sumbitFailed, 0,5);

        grid.setVgap(10);
        grid.setHgap(5);

        Scene scene = new Scene(grid, 1360, 720);
        addStudentStage.setScene(scene);
        addStudentStage.show();

        //Action Button
        submitButton.setOnAction(event -> {
            if (nimField.getText().length() == 15) {
                Admin adminObj = new Admin();

                Student.arr_userStudent.add(new Student.UserStudent(nameField.getText(), nimField.getText(), fakultasField.getText(), jurusanField.getText()));
                adminObj.menu();
                addStudentStage.close();

            } else {
                sumbitFailed.setVisible(true);
            }
        });

    }

    public void displaystudent() {
        // Membuat stage baru
        Stage displayStudentStage = new Stage();
        displayStudentStage.setTitle("Saff Library - Daftar Mahasiswa");

        //Label
        Label sceneTitle = new Label("Daftar Mahasiswa");

        //Font Style
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

        //Font Color
        sceneTitle.setStyle("-fx-text-fill: #FFC0CB;");

        // Membuat ListView untuk menampilkan mahasiswa
        ListView<String> listView = new ListView<>();

        for (Student.UserStudent i : Student.arr_userStudent) {
            String studentInfo = "Nama     : " + i.nama +"\n" +
                    "NIM      : " + i.nim + "\n" +
                    "Fakultas : " + i.fakultas + "\n" +
                    "Prodi    : " + i.prodi + "\n" +
                    "===========================";
            listView.getItems().add(studentInfo);
        }

        // Membuat tombol kembali ke menu admin
        Button backButton = new Button("Kembali ke Menu Admin");
        backButton.setOnAction(event -> {
            Admin admin = new Admin();
            admin.menu();
            displayStudentStage.close();
        });

        //Grid Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        grid.add(sceneTitle,0,0);
        grid.add(listView,0,1);
        grid.add(backButton, 0, 2);

        grid.setVgap(5);

        Scene scene = new Scene(grid, 1360, 720);
        displayStudentStage.setScene(scene);
        displayStudentStage.show();
    }

    public void addBook() {
        // Membuat form baru
        Stage addBookStage = new Stage();
        addBookStage.setTitle("Tambah Buku");

        //Label
        Label sceneTitle = new Label("Tambah Buku");
        Label bookIdLabel = new Label("ID Buku:");
        Label titleLabel = new Label("Judul:");
        Label authorLabel = new Label("Penulis:");
        Label categoryLabel = new Label("Kategori:");
        Label stockLabel = new Label("Stok:");

        //Field
        TextField bookIdField = new TextField(generateId()); // Isi field ID Buku dengan hasil generateId()
        TextField titleField = new TextField();
        TextField authorField = new TextField();
        ComboBox<String> categoryComboBox = new ComboBox<>(); // ComboBox untuk kategori buku
        TextField stockField = new TextField();

        // Menambahkan opsi kategori buku
        categoryComboBox.getItems().addAll("History Book", "Story Book", "Text Book");

        //Font Style
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        bookIdLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        titleLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        authorLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        categoryLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));
        stockLabel.setFont(Font.font("Calibri Body", FontWeight.NORMAL, 15));

        //Font Color
        sceneTitle.setStyle("-fx-text-fill: #FFC0CB;");

        //Button
        Button submitButton = new Button("Submit");

        //Grid Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add(sceneTitle, 0, 0);

        grid.add(bookIdLabel, 0, 1);
        grid.add(titleLabel, 0, 2);
        grid.add(authorLabel, 0, 3);
        grid.add(categoryLabel, 0, 4);
        grid.add(stockLabel, 0, 5);

        grid.add(bookIdField, 1, 1);
        grid.add(titleField, 1, 2);
        grid.add(authorField, 1, 3);
        grid.add(categoryComboBox, 1, 4); // Menggunakan ComboBox untuk kategori buku
        grid.add(stockField, 1, 5);

        grid.add(submitButton, 1, 6);

        grid.setVgap(10);
        grid.setHgap(5);

        Scene scene = new Scene(grid, 400, 300);
        addBookStage.setScene(scene);
        addBookStage.show();

        submitButton.setOnAction(event -> {
            // Cek apakah semua field sudah diisi
            if (!titleField.getText().isEmpty() && !authorField.getText().isEmpty()
                    && !categoryComboBox.getSelectionModel().isEmpty() && !stockField.getText().isEmpty()) {
                // Tambahkan buku ke dalam daftar buku
                String bookId = bookIdField.getText(); // Gunakan ID yang digenerate
                String title = titleField.getText();
                String author = authorField.getText();
                String category = categoryComboBox.getValue(); // Mendapatkan nilai kategori dari ComboBox
                int stock = Integer.parseInt(stockField.getText());

                Book.arr_bookList.add(new Book(bookId, title, author, category, stock));

                // Tutup stage setelah buku ditambahkan
                addBookStage.close();

                // Tampilkan pesan sukses
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Book added successfully!");

                // Tambahkan tombol "Back" untuk kembali ke menu utama
                ButtonType buttonTypeBack = new ButtonType("Back");
                alert.getButtonTypes().setAll(buttonTypeBack);

                // Handle aksi saat tombol "Back" ditekan
                alert.setOnCloseRequest(event1 -> {
                    if (alert.getResult() == buttonTypeBack) {
                        // Kembali ke menu utama
                        menu();
                    }
                });

                alert.showAndWait();
            } else {
                // Jika ada field yang kosong, tampilkan pesan kesalahan
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Silakan isi semua kolom!");

                alert.showAndWait();
            }
        });
    }

    public void displayBook() {
        // Membuat stage baru
        Stage displayBookStage = new Stage();
        displayBookStage.setTitle("Saff Library - Daftar Buku");

        //Label
        Label sceneTitle = new Label("Daftar Buku");
        //Font Style
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        //Font Color
        sceneTitle.setStyle("-fx-text-fill: #FFC0CB;");

        // Membuat TableView untuk menampilkan buku dalam bentuk tabel
        TableView<Book> tableView = new TableView<>();

        // Menambahkan kolom untuk atribut buku
        TableColumn<Book, String> bookIdCol = new TableColumn<>("ID Buku");
        bookIdCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBookId()));

        TableColumn<Book, String> titleCol = new TableColumn<>("Judul");
        titleCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));

        TableColumn<Book, String> authorCol = new TableColumn<>("Penulis");
        authorCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));

        TableColumn<Book, String> categoryCol = new TableColumn<>("Kategori");
        categoryCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));

        TableColumn<Book, String> stockCol = new TableColumn<>("Stok");
        stockCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getStock())));

        // Menambahkan kolom-kolom ke dalam tabel
        tableView.getColumns().addAll(bookIdCol, titleCol, authorCol, categoryCol, stockCol);

        // Menambahkan data buku ke dalam tabel
        tableView.getItems().addAll(Book.arr_bookList);

        // Membuat VBox untuk menampung komponen
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.getChildren().addAll(sceneTitle, tableView);

        // Membuat tombol kembali
        Button backButton = new Button("Kembali");
        backButton.setOnAction(event -> {
            displayBookStage.close();
            Admin admin = new Admin();
            admin.menu(); // Memanggil kembali menu admin
        });

        // Menambahkan tombol kembali di bawah tabel
        vbox.getChildren().add(backButton);

        // Membuat Scene
        Scene scene = new Scene(vbox, 1360, 720);
        displayBookStage.setScene(scene);
        displayBookStage.show();
    }

    public String generateId () {
        Random random = new Random();

        StringBuilder idTengah = new StringBuilder();
        StringBuilder idAkhir = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            idTengah.append(random.nextInt(10));
            idAkhir.append(random.nextInt(10));

        }
        return ("SAFF-" + idTengah + "-" + idAkhir);

    }


}

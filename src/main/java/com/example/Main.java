package com.example;
public class Main {
    public static void main(String[] args) {
        // Przykład połączenia do bazy danych
        String dbURL = "jdbc:mysql://localhost:3306/studentdb";
        String dbUsername = "root";
        String dbPassword = "1234";

        StudentManager studentManager = new StudentManagerImpl(dbURL, dbUsername, dbPassword);

        // Uruchomienie GUI
        StudentManagerGUI gui = new StudentManagerGUI(studentManager);
        gui.setVisible(true);
    }
}

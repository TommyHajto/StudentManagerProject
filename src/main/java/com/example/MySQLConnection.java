package com.example;
//KLASA AKTUALNIE NIE JEST UŻYWANA, GDYŻ MAIN.JAVA WYWOŁUJE POŁĄCZENIE,//
//LECZ W PRZYPADKU DALSZEGO ROZWOJU PROGRAMU, MOŻE BYĆ PRZYDATNA//
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    public static Connection getDBConnection(String dbPath, String user, String password) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbPath, user, password);
            System.out.println("Połączenie z bazą danych zostało nawiązane.");
        } catch (SQLException e) {
            System.out.println("Błąd podczas nawiązywania połączenia: " + e.getMessage());
        }
        return connection;
    }
}
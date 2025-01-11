package com.example;


import java.sql.*;
import java.util.ArrayList;

public class StudentManagerImpl implements StudentManager {
    private ArrayList<Student> students;
    private Connection connection;

    public StudentManagerImpl(String dbURL, String dbUsername, String dbPassword) {
        this.students = new ArrayList<>();
        try {
            this.connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            System.out.println("Połączenie z bazą danych nawiązane.");
            createTableIfNotExists();  // Sprawdzamy i tworzymy tabelę, jeśli nie istnieje
            loadStudentsFromDatabase();  // Załaduj studentów z bazy danych przy starcie
        } catch (SQLException e) {
            System.err.println("Błąd podczas łączenia z bazą danych: " + e.getMessage());
        }
    }

    private void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS students ("
                + "studentID VARCHAR(50) PRIMARY KEY, "
                + "firstName VARCHAR(100), "
                + "lastName VARCHAR(100), "
                + "age INT, "
                + "initialGrade DOUBLE)";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(createTableSQL);
            System.out.println("Tabela 'students' została utworzona lub już istnieje.");
        } catch (SQLException e) {
            System.err.println("Błąd podczas tworzenia tabeli: " + e.getMessage());
        }
    }

    @Override
    public void addStudent(Student student) {
        students.add(student);
        String sql = "INSERT INTO students (studentID, firstName, lastName, age, initialGrade) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getStudentID());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setInt(4, student.getAge());
            statement.setDouble(5, student.getInitialGrade());
            statement.executeUpdate();
            System.out.println("Dodano studenta do bazy danych: " + student.getStudentID());
        } catch (SQLException e) {
            System.err.println("Błąd podczas dodawania studenta do bazy danych: " + e.getMessage());
        }
    }

    @Override
    public void removeStudent(String studentID) {
        students.removeIf(student -> student.getStudentID().equals(studentID));
        String sql = "DELETE FROM students WHERE studentID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, studentID);
            statement.executeUpdate();
            System.out.println("Usunięto studenta z bazy danych: " + studentID);
        } catch (SQLException e) {
            System.err.println("Błąd podczas usuwania studenta z bazy danych: " + e.getMessage());
        }
    }

    @Override
    public void updateStudent(String studentID, String newFirstName, String newLastName, int newAge, double newGrade) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                student.setFirstName(newFirstName);
                student.setLastName(newLastName);
                student.setAge(newAge);
                student.setInitialGrade(newGrade);
                break;
            }
        }

        String sql = "UPDATE students SET firstName = ?, lastName = ?, age = ?, initialGrade = ? WHERE studentID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newFirstName);
            statement.setString(2, newLastName);
            statement.setInt(3, newAge);
            statement.setDouble(4, newGrade);
            statement.setString(5, studentID);
            statement.executeUpdate();
            System.out.println("Zaktualizowano dane studenta w bazie danych: " + studentID);
        } catch (SQLException e) {
            System.err.println("Błąd podczas aktualizacji studenta w bazie danych: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Student> displayAllStudents() {
        return new ArrayList<>(students);
    }

    @Override
    public double calculateAverageGrade() {
        double sum = 0;
        for (Student student : students) {
            sum += student.calculateAverage();
        }
        return students.size() > 0 ? sum / students.size() : 0;
    }

    @Override
    public void loadStudentsFromDatabase() {
        String sql = "SELECT * FROM students";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            students.clear();
            while (resultSet.next()) {
                String studentID = resultSet.getString("studentID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                int age = resultSet.getInt("age");
                double initialGrade = resultSet.getDouble("initialGrade");
                students.add(new Student(firstName, lastName, age, initialGrade, studentID));
            }
            System.out.println("Załadowano studentów z bazy danych.");
        } catch (SQLException e) {
            System.err.println("Błąd podczas ładowania studentów z bazy danych: " + e.getMessage());
        }
    }
}

package com.example;

import java.util.ArrayList;

public interface StudentManager {
    void addStudent(Student student);
    void removeStudent(String studentID);
    void updateStudent(String studentID, String newFirstName, String newLastName, int newAge, double newGrade);
    ArrayList<Student> displayAllStudents();
    double calculateAverageGrade();
    void loadStudentsFromDatabase();
}

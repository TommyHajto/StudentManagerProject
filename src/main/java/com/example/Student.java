package com.example;


public class Student {
    private String firstName;
    private String lastName;
    private int age;
    private double initialGrade;
    private String studentID;

    public Student(String firstName, String lastName, int age, double initialGrade, String studentID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.initialGrade = initialGrade;
        this.studentID = studentID;
    }

    // Gettery i settery
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getInitialGrade() {
        return initialGrade;
    }

    public void setInitialGrade(double initialGrade) {
        this.initialGrade = initialGrade;
    }

    // Metoda wyświetlająca dane studenta
    public String displayInfo() {
        return "ID: " + studentID + ", Imię: " + firstName + ", Nazwisko: " + lastName + ", Wiek: " + age + ", Ocena: " + initialGrade;
    }

    // Przykładowa metoda obliczania średniej
    public double calculateAverage() {
        return initialGrade;
    }
}

package com.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentManagerGUI extends JFrame {
    private StudentManager studentManager;
    private JTextArea studentListTextArea;
    private JTextField studentIDField, firstNameField, lastNameField, ageField, gradeField;

    public StudentManagerGUI(StudentManager studentManager) {
        this.studentManager = studentManager;

        setTitle("Student Manager");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        studentListTextArea = new JTextArea();
        studentListTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(studentListTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));

        inputPanel.add(new JLabel("Student ID:"));
        studentIDField = new JTextField();
        inputPanel.add(studentIDField);

        inputPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        inputPanel.add(firstNameField);

        inputPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        inputPanel.add(lastNameField);

        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("Initial Grade:"));
        gradeField = new JTextField();
        inputPanel.add(gradeField);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new AddStudentButtonListener());
        inputPanel.add(addButton);

        JButton updateButton = new JButton("Update Student");
        updateButton.addActionListener(new UpdateStudentButtonListener());
        inputPanel.add(updateButton);

        JButton removeButton = new JButton("Remove Student");
        removeButton.addActionListener(new RemoveStudentButtonListener());
        inputPanel.add(removeButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> updateStudentList());
        inputPanel.add(refreshButton);

        add(inputPanel, BorderLayout.SOUTH);

        updateStudentList(); // Załaduj dane studentów przy starcie aplikacji
    }

    private void updateStudentList() {
        studentManager.loadStudentsFromDatabase(); // Załaduj dane studentów
        ArrayList<Student> students = studentManager.displayAllStudents(); // Pobierz listę studentów
        studentListTextArea.setText(""); // Wyczyść istniejącą listę
        for (Student student : students) {
            studentListTextArea.append(student.displayInfo() + "\n"); // Dodaj każdego studenta do wyświetlania
        }
    }

    private class AddStudentButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String studentID = studentIDField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            int age = Integer.parseInt(ageField.getText());
            double grade = Double.parseDouble(gradeField.getText());

            Student student = new Student(firstName, lastName, age, grade, studentID);
            studentManager.addStudent(student);
            updateStudentList(); // Odśwież listę studentów

            studentIDField.setText("");
            firstNameField.setText("");
            lastNameField.setText("");
            ageField.setText("");
            gradeField.setText("");
        }
    }

    private class UpdateStudentButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String studentID = studentIDField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            int age = Integer.parseInt(ageField.getText());
            double grade = Double.parseDouble(gradeField.getText());

            studentManager.updateStudent(studentID, firstName, lastName, age, grade);
            updateStudentList(); // Odśwież listę studentów

            studentIDField.setText("");
            firstNameField.setText("");
            lastNameField.setText("");
            ageField.setText("");
            gradeField.setText("");
        }
    }

    private class RemoveStudentButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String studentID = studentIDField.getText();
            studentManager.removeStudent(studentID);
            updateStudentList(); // Odśwież listę studentów

            studentIDField.setText("");
        }
    }
}

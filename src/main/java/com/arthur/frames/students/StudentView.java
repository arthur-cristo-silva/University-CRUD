package com.arthur.frames.students;

import com.arthur.entity.Student;

import javax.swing.*;

public class StudentView extends JFrame {
    private JLabel name;
    private JLabel course;
    private JLabel absences;
    private JLabel schedule;
    private JPanel mainPanel;
    private JButton backButton;

    public StudentView(Student student) {
        setContentPane(mainPanel);
        setTitle(student.getName());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(480, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        name.setText(student.getName());
        course.setText(String.format("%dº semestre de %s", student.getPeriod(), student.getCourse()));
        schedule.setText("Horário: " + student.getSchedule());
        absences.setText("Faltas: " + student.getAbsences());
        backButton.addActionListener(e -> {
            new StudentsFrame();
            dispose();
        });
    }
}

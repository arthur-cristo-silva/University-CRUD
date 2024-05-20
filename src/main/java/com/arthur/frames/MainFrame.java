package com.arthur.frames;

import com.arthur.frames.professors.ProfessorsFrame;
import com.arthur.frames.students.StudentsFrame;

import javax.swing.*;

public class MainFrame extends JFrame {
    private JButton professorBTN;
    private JPanel mainPanel;
    private JButton studentsBTN;
    private JLabel titleTXT;

    public MainFrame() {
        setContentPane(mainPanel);
        setTitle("Universidade");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(480, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        studentsBTN.addActionListener(e -> {
            new StudentsFrame();
            dispose();
        });
        professorBTN.addActionListener(e -> {
            new ProfessorsFrame();
            dispose();
        });
    }
}

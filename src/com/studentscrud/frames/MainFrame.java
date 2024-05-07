package com.studentscrud.frames;

import com.studentscrud.frames.professors.ProfessorsFrame;
import com.studentscrud.frames.students.StudentsFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JLabel titleTXT;
    private JButton professorBTN;
    private JPanel mainPanel;
    private JButton studentsBTN;

    public MainFrame() {
        setContentPane(mainPanel);
        setTitle("Universidade");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(480, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        studentsBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentsFrame();
                dispose();
            }
        });
        professorBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfessorsFrame();
                dispose();
            }
        });
    }
}

package com.arthur.frames.professors;

import com.arthur.entity.Professor;
import com.arthur.entity.Student;
import com.arthur.frames.students.StudentsFrame;

import javax.swing.*;

public class ProfessorView extends JFrame {
    private JLabel name;
    private JLabel email;
    private JLabel phoneNumber;
    private JPanel mainPanel;
    private JButton backButton;
    private JLabel wl;

    public ProfessorView(Professor professor) {
        setContentPane(mainPanel);
        setTitle(professor.getName());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(480, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        name.setText(professor.getName());
        phoneNumber.setText("Telefone: "+professor.getPhoneNumber());
        email.setText("E-mail: "+professor.getEmail());
        wl.setText("Carga Horária: "+professor.getWorkload()+" horas semanais");
        backButton.addActionListener(e -> {
            new ProfessorsFrame();
            dispose();
        });
    }
}

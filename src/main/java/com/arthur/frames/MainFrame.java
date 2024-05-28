package com.arthur.frames;

import com.arthur.frames.professors.ProfessorsFrame;
import com.arthur.frames.students.StudentsFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JButton professorBTN;
    private JPanel mainPanel;
    private JButton studentsBTN;
    private JLabel titleTXT;
    private JButton classesBTN;
    private JButton sairBTN;

    // Janela principal
    public MainFrame() {
        setContentPane(mainPanel);
        setTitle("Universidade");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(480, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        // Vai para a janela de estudantes
        studentsBTN.addActionListener(e -> {
            new StudentsFrame();
            dispose();
        });
        // Vai para a janela de professores
        professorBTN.addActionListener(e -> {
            new ProfessorsFrame();
            dispose();
        });
        // Fecha o programa
        sairBTN.addActionListener(e -> {
            JOptionPane.showMessageDialog(mainPanel, "Saindo do programa...");
            dispose();
        });
    }
}

package com.studentscrud.frames.professors;

import com.studentscrud.dao.ProfessorDAO;
import com.studentscrud.dao.StudentDAO;
import com.studentscrud.objects.Professor;
import com.studentscrud.objects.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

public class UpdateProfessor extends JFrame {

    // Elements
    private JLabel titleTXT;
    private JLabel nameTXT;
    private JTextField nameInput;
    private JLabel ageTXT;
    private JTextField ageInput;
    private JLabel emailTXT;
    private JTextField emailInput;
    private JLabel workloadTXT;
    private JTextField workloadInput;
    private JButton addBTN;
    private JButton backButton;
    private JPanel mainPanel;

    public UpdateProfessor(Long ra, String name, int age, String email, int workload) {
        setContentPane(mainPanel);
        setTitle("Atualizar Professor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        nameInput.setText(name);
        ageInput.setText(String.valueOf(age));
        emailInput.setText(email);
        workloadInput.setText(String.valueOf(workload));
        addBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameInput.getText();
                    int age = Objects.equals(ageInput.getText(), "") ? 0 : Integer.parseInt(ageInput.getText());
                    String email = emailInput.getText();
                    int workload = Objects.equals(workloadInput.getText(), "") ? 0 : Integer.parseInt(workloadInput.getText());
                    if (name.isEmpty() || age == 0 || email.isEmpty() || workload == 0) {
                        throw new Exception();
                    }
                    Professor professor = new Professor(ra, name, age, email, workload);
                    ProfessorDAO professorDAO = new ProfessorDAO();
                    professorDAO.update(professor);
                    JOptionPane.showMessageDialog(mainPanel, "Professor atualizado!");
                    new ProfessorsFrame();
                    dispose();
                } catch (SQLException g) {
                    JOptionPane.showMessageDialog(mainPanel, "Erro ao atualizar professor no banco de dados.");
                } catch (Exception h) {
                    JOptionPane.showMessageDialog(mainPanel, "Por favor, insira dados válidos.");
                    throw new RuntimeException(h);
                }
            }
        });
        backButton.addActionListener(e -> {
            new ProfessorsFrame();
            dispose();
        });
    }
}
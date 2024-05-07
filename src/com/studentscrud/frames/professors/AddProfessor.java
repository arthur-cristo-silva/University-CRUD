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

public class AddProfessor extends JFrame {

    // Elements
    private JLabel titleTXT;
    private JTextField nameInput;
    private JTextField ageInput;
    private JTextField workloadInput;
    private JTextField emailInput;
    private JLabel nameTXT;
    private JLabel emailTXT;
    private JLabel ageTXT;
    private JLabel workloadTXT;
    private JButton addBTN;
    private JButton backButton;
    private JPanel mainPanel;

    public AddProfessor() {
        setContentPane(mainPanel);
        setTitle("Adicionar Novo Professor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        addBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameInput.getText();
                    int age = Objects.equals(ageInput.getText(), "") ? 0 : Integer.parseInt(ageInput.getText());
                    String email = emailInput.getText();
                    int workload = Objects.equals(ageInput.getText(), "") ? 0 : Integer.parseInt(workloadInput.getText());
                    if (name.isEmpty() || age == 0 || email.isEmpty() || workload == 0) {
                        throw new Exception();
                    }
                    Professor professor = new Professor(name, age, email, workload);
                    ProfessorDAO professorDAO = new ProfessorDAO();
                    professorDAO.save(professor);
                    JOptionPane.showMessageDialog(mainPanel, "Professor adicionado!");
                    new ProfessorsFrame();
                    dispose();
                } catch (SQLException g) {
                    JOptionPane.showMessageDialog(mainPanel, "Erro ao salvar professor no banco de dados.");
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
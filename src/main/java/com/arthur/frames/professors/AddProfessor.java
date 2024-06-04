package com.arthur.frames.professors;

import com.arthur.dao.ProfessorDAO;
import com.arthur.entity.Professor;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Objects;

public class AddProfessor extends JFrame {

    // Elements
    private JLabel titleTXT;
    private JLabel nameTXT;
    private JTextField nameInput;
    private JLabel phoneNumberTXT;
    private JTextField phoneNumberInput;
    private JLabel emailTXT;
    private JTextField emailInput;
    private JLabel workloadTXT;
    private JTextField workloadInput;
    private JButton addBTN;
    private JButton backButton;
    private JPanel mainPanel;

    // Cadastra novo professor no banco de dados
    public AddProfessor() {
        setContentPane(mainPanel);
        setTitle("Atualizar Professor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        // Cadastra novo professor
        addBTN.addActionListener(e -> {
            try {
                ProfessorDAO.save(getProfessor());
                JOptionPane.showMessageDialog(mainPanel, "Professor cadastrado!");
                new ProfessorsFrame();
                dispose();
            } catch (SQLException g) {
                JOptionPane.showMessageDialog(mainPanel, "Erro ao atualizar professor no banco de dados.");
            } catch (Exception h) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, insira dados válidos.");
                throw new RuntimeException(h);
            }
        });
        // Volta para janela anterior
        backButton.addActionListener(e -> {
            new ProfessorsFrame();
            dispose();
        });
    }

    // Recolhe informações e retornar um objeto de professor
    private Professor getProfessor() throws Exception {
        String name = nameInput.getText();
        String phoneNumber = phoneNumberInput.getText();
        String email = emailInput.getText();
        int workload = Objects.equals(workloadInput.getText(), "") ? 0 : Integer.parseInt(workloadInput.getText());
        if (name.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || workload == 0) {
            throw new Exception();
        }
        return new Professor(name, phoneNumber, email, workload);
    }
}
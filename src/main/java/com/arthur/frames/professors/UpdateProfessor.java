package com.arthur.frames.professors;

import com.arthur.dao.ProfessorDAO;
import com.arthur.entity.Professor;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Objects;

public class UpdateProfessor extends JFrame {

    // Elementos da janela
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

    // Janela para atualizar professor
    public UpdateProfessor(Professor professor) {
        setContentPane(mainPanel);
        setTitle("Atualizar Professor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        nameInput.setText(professor.getName());
        emailInput.setText(professor.getEmail());
        phoneNumberInput.setText(professor.getPhoneNumber());
        workloadInput.setText(professor.getWorkload().toString());
        addBTN.addActionListener(e -> {
            try {
                Professor newProfessor = getProfessor(professor.getRa());
                ProfessorDAO.update(newProfessor);
                JOptionPane.showMessageDialog(mainPanel, "Professor atualizado!");
                new ProfessorsFrame();
                dispose();
            } catch (SQLException g) {
                JOptionPane.showMessageDialog(mainPanel, "Erro ao atualizar professor no banco de dados.");
            } catch (Exception h) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, insira dados válidos.");
                throw new RuntimeException(h);
            }
        });
        // Voltar para janela anterior
        backButton.addActionListener(e -> {
            new ProfessorsFrame();
            dispose();
        });
    }

    // Recolhe informações e retornar um objeto de professor
    private Professor getProfessor(Long ra) throws Exception {
        String name = nameInput.getText();
        String phoneNumber = phoneNumberInput.getText();
        String email = emailInput.getText();
        int workload = Objects.equals(workloadInput.getText(), "") ? 0 : Integer.parseInt(workloadInput.getText());
        if (name.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || workload < 0) {
            throw new Exception();
        }
        return new Professor(ra, name, phoneNumber, email, workload);
    }
}
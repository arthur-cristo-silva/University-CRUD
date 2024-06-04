package com.arthur.frames.professors;

import com.arthur.dao.ProfessorDAO;
import com.arthur.entity.Professor;

import javax.swing.*;
import java.sql.SQLException;
import java.util.InputMismatchException;

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
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        nameInput.setText(professor.getName());
        emailInput.setText(professor.getEmail());
        phoneNumberInput.setText(professor.getPhoneNumber());
        workloadInput.setText(professor.getWorkload().toString());
        addBTN.addActionListener(e -> {
            try {
                ProfessorDAO.update(Professor.getProfessor(professor.getRa(), nameInput.getText(), emailInput.getText(), phoneNumberInput.getText(), Integer.parseInt(workloadInput.getText())));
                JOptionPane.showMessageDialog(mainPanel, "Professor atualizado!");
                new ProfessorsFrame();
                dispose();
            } catch (SQLException g) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro ao tentar se conectar com o banco de dados.");
            } catch (InputMismatchException | NumberFormatException f) {
                JOptionPane.showMessageDialog(mainPanel, f.getMessage());
            } catch (Exception h) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, insira dados válidos.");
            }
        });
        // Voltar para janela anterior
        backButton.addActionListener(e -> {
            new ProfessorsFrame();
            dispose();
        });
    }
}
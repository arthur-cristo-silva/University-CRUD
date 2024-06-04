package com.arthur.frames.professors;

import com.arthur.dao.ProfessorDAO;
import com.arthur.entity.Professor;

import javax.swing.*;
import java.sql.SQLException;
import java.util.InputMismatchException;

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
        setTitle("Adicionar Professor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        // Cadastra novo professor
        addBTN.addActionListener(e -> {
            try {
                ProfessorDAO.save(Professor.getProfessor(nameInput.getText(), emailInput.getText(), phoneNumberInput.getText(), Integer.parseInt(workloadInput.getText())));
                JOptionPane.showMessageDialog(mainPanel, "Professor cadastrado!");
                new ProfessorsFrame();
                dispose();
            } catch (SQLException g) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro ao tentar se conectar com o banco de dados.");
            } catch (InputMismatchException | NumberFormatException g) {
                System.out.println(g.getMessage());
            } catch (Exception g) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, um erro ocorreu inesperado..");
            }
        });
        // Volta para janela anterior
        backButton.addActionListener(e -> {
            new ProfessorsFrame();
            dispose();
        });
    }
}
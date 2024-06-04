package com.arthur.frames.ucs;

import com.arthur.dao.UcDAO;
import com.arthur.entity.Uc;

import javax.swing.*;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class AddUc extends JFrame {

    // Elementos da janela
    private JLabel titleTXT;
    private JTextField nameInput;
    private JTextField periodInput;
    private JTextField absencesInput;
    private JComboBox scheduleInput;
    private JTextField typeInput;
    private JButton addBTN;
    private JButton backButton;
    private JPanel mainPanel;
    private JLabel nameTXT;
    private JLabel typeTXT;

    // Cria novo aluno no banco de dados
    public AddUc() {
        setContentPane(mainPanel);
        setTitle("Adicionar UC");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        addBTN.addActionListener(e -> {
            try {
                UcDAO.save(Uc.getUc(nameInput.getText(), typeInput.getText()));
                JOptionPane.showMessageDialog(mainPanel, "UC adicionada!");
                new UcFrame();
                dispose();
            } catch (SQLException f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro ao tentar se conectar com o banco de dados.");
            } catch (InputMismatchException f) {
                JOptionPane.showMessageDialog(mainPanel, f.getMessage());
            } catch (Exception f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro inesperado.");
                f.printStackTrace();
            }
        });
        // Volta para a janela anterior
        backButton.addActionListener(e -> {
            new UcFrame();
            dispose();
        });
    }
}
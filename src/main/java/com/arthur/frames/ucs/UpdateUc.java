package com.arthur.frames.ucs;

import com.arthur.dao.UcDAO;
import com.arthur.entity.Uc;

import javax.swing.*;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class UpdateUc extends JFrame {

    // Elementos da janela
    private JLabel titleTXT;
    private JTextField nameInput;
    private JTextField courseInput;
    private JButton addBTN;
    private JButton backButton;
    private JPanel mainPanel;
    private JLabel nameTXT;
    private JLabel typeTXT;
    private JTextField typeInput;

    // Atualiza informações de estudante do banco de dados
    public UpdateUc(Uc uc) {
        setContentPane(mainPanel);
        setTitle("Atualizar Aluno");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        // Preenche nos inputs o nome e curso do aluno
        nameInput.setText(uc.getName());
        typeInput.setText(uc.getType());
        addBTN.addActionListener(e -> {
            try {
                UcDAO.update(Uc.getUc(nameInput.getText(), typeInput.getText()));
                JOptionPane.showMessageDialog(mainPanel, "UC atualizada!");
                new UcFrame();
                dispose();
            } catch (SQLException f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro ao tentar se conectar com o banco de dados.");
            } catch (InputMismatchException f) {
                JOptionPane.showMessageDialog(mainPanel, f.getMessage());
            } catch (Exception f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, um erro ocorreu inesperado.");
                f.printStackTrace();
            }
        });
        // Volta para janela anterior
        backButton.addActionListener(e -> {
            new UcFrame();
            dispose();
        });
    }
}
package com.arthur.frames.ucs;

import com.arthur.dao.UcDAO;
import com.arthur.entity.Uc;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Random;

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
        setSize(480, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        addBTN.addActionListener(e -> {
            try {
                UcDAO.save(getUc());
                JOptionPane.showMessageDialog(mainPanel, "UC adicionada!");
                new UcFrame();
                dispose();
            } catch (SQLException g) {
                JOptionPane.showMessageDialog(mainPanel, "Erro ao atualizar UC no banco de dados.");
            } catch (Exception h) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, insira dados válidos.");
                throw new RuntimeException(h);
            }
        });
        // Volta para a janela anterior
        backButton.addActionListener(e -> {
            new UcFrame();
            dispose();
        });
    }

    // Metodo para colher informações do aluno
    private Uc getUc() throws Exception {
        String name = nameInput.getText();
        String tipo = typeInput.getText();
        Random r = new Random();
        String code = name.toUpperCase().substring(0,4) + (""+r.nextInt(9) + r.nextInt(9) + r.nextInt(9));
        if (name.isEmpty() || tipo.isEmpty()) {
            throw new Exception();
        }
        return new Uc(code, name, tipo);
    }

}
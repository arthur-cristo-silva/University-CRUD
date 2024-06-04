package com.arthur.frames.ucs;

import com.arthur.dao.UcDAO;
import com.arthur.entity.Uc;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Random;

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
                UcDAO.update(getUc());
                JOptionPane.showMessageDialog(mainPanel, "UC atualizada!");
                new UcFrame();
                dispose();
            } catch (SQLException g) {
                JOptionPane.showMessageDialog(mainPanel, "Erro ao atualizar UC no banco de dados.");
            } catch (Exception h) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, insira dados válidos.");
                throw new RuntimeException(h);
            }
        });
        // Volta para janela anterior
        backButton.addActionListener(e -> {
            new UcFrame();
            dispose();
        });
    }

    // Metodo para colher informações da UC
    private Uc getUc() throws Exception {
        String name = nameInput.getText();
        String type = typeInput.getText();
        Random r = new Random();
        String code = name.toUpperCase().substring(0, 4) + ("" + r.nextInt(9) + r.nextInt(9) + r.nextInt(9));
        if (name.isEmpty() || type.isEmpty()) {
            throw new Exception();
        }
        return new Uc(code, name, type);
    }
}
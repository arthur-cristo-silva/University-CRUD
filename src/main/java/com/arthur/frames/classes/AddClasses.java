package com.arthur.frames.classes;

import com.arthur.dao.ClassesDAO;
import com.arthur.dao.ProfessorDAO;
import com.arthur.dao.UcDAO;
import com.arthur.entity.Classes;
import com.arthur.entity.Professor;
import com.arthur.entity.Uc;

import javax.swing.*;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;

public class AddClasses extends JFrame {

    // Elementos da janela
    private JLabel titleTXT;
    private JTextField codeInput;
    private JButton addBTN;
    private JButton backButton;
    private JPanel mainPanel;
    private JLabel nameTXT;
    private JLabel ucTXT;
    private JComboBox<String> ucCB;
    private JComboBox<String> professorCB;
    private JLabel professorTXT;

    // Cria nova turma no banco de dados
    public AddClasses() {
        setContentPane(mainPanel);
        setTitle("Adicionar Turma");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);
        try {
            List<Professor> professors = ProfessorDAO.findAll();
            for (Professor professor : professors) {
                professorCB.addItem(professor.getName());
            }
            List<Uc> ucs = UcDAO.getAll();
            for (Uc uc : ucs) {
                ucCB.addItem(uc.getCode());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro ao tentar se conectar com o banco de dados.");
        }
        setVisible(true);
        addBTN.addActionListener(e -> {
            try {
                ClassesDAO.save(getClasses());
                JOptionPane.showMessageDialog(mainPanel, "Turma cadastrada!");
                new ClassesFrame();
                dispose();
            } catch (SQLException g) {
                JOptionPane.showMessageDialog(mainPanel, "Erro ao atualizar turma no banco de dados.");
            } catch (InputMismatchException f) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, digite um código para a turma.");
            } catch (Exception h) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, insira dados válidos.");
            }
        });
        // Volta para a janela anterior
        backButton.addActionListener(e -> {
            new ClassesFrame();
            dispose();
        });
    }

    // Metodo para colher informações da turma
    private Classes getClasses() throws Exception {
        String code = codeInput.getText();
        String uc = Objects.requireNonNull(ucCB.getSelectedItem()).toString();
        long prof = ProfessorDAO.getRaByName(Objects.requireNonNull(professorCB.getSelectedItem()).toString());
        if (code.isEmpty()) {
            throw new InputMismatchException();
        }
        return new Classes(code, prof, uc);
    }

}
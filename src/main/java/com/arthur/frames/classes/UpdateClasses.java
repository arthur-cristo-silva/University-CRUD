package com.arthur.frames.classes;

import com.arthur.dao.ClassesDAO;
import com.arthur.dao.ProfessorDAO;
import com.arthur.dao.UcDAO;
import com.arthur.entity.Classes;
import com.arthur.entity.Professor;
import com.arthur.entity.Uc;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class UpdateClasses extends JFrame {

    // Elementos da janela
    private JButton addBTN;
    private JButton backButton;
    private JPanel mainPanel;
    private JTextField codeInput;
    private JComboBox<String> ucCB;
    private JComboBox<String> professorCB;
    private JButton studentsBTN;
    private JLabel professorTXT;
    private JLabel ucTXT;
    private JLabel nameTXT;
    private JLabel titleTXT;

    // Atualiza informações de turma do banco de dados
    public UpdateClasses(Classes classes) throws Exception {
        setContentPane(mainPanel);
        setTitle("Atualizar Turma");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        codeInput.setText(classes.getCode());
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
        // Preenche nos inputs a uc e professor do turma
        ucCB.setSelectedItem(classes.getUc());
        professorCB.setSelectedItem(ProfessorDAO.getNameByRa(classes.getProfessor()));
        addBTN.addActionListener(e -> {
            try {
                ClassesDAO.update(getClasses());
                JOptionPane.showMessageDialog(mainPanel, "Turma atualizada!");
                new ClassesFrame();
                dispose();
            } catch (SQLException g) {
                JOptionPane.showMessageDialog(mainPanel, "Erro ao atualizar turma no banco de dados.");
            } catch (Exception h) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, insira dados válidos.");
                throw new RuntimeException(h);
            }
        });
        // Volta para janela anterior
        backButton.addActionListener(e -> {
            new ClassesFrame();
            dispose();
        });
        studentsBTN.addActionListener(e -> {

        });
    }

    // Metodo para colher informações da UC
    private Classes getClasses() throws Exception {
        String code = codeInput.getText();
        String uc = Objects.requireNonNull(ucCB.getSelectedItem()).toString();
        long professor = ProfessorDAO.getRaByName(Objects.requireNonNull(professorCB.getSelectedItem()).toString());
        if (code.isEmpty()) {
            throw new Exception();
        }
        return new Classes(code, professor, uc);
    }
}
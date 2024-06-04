package com.arthur.frames.classes;

import com.arthur.dao.ClassesDAO;
import com.arthur.dao.ProfessorDAO;
import com.arthur.dao.UcDAO;
import com.arthur.entity.Classes;
import com.arthur.frames.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class ClassesFrame extends JFrame {

    private JButton addBTN;
    private JButton updateBTN;
    private JButton deleteBTN;
    private JLabel titleTXT;
    private JButton searchCodeBTN;
    private JPanel mainPanel;
    private JTable table1;
    private JScrollPane jScroll;
    private JButton backBTN;
    private JButton getAllBTN;
    private JTextField searchInput;
    private JSpinner spinner1;
    private JScrollPane scrollPane;

    // Janela para gerenciar turmas
    public ClassesFrame() {
        setContentPane(mainPanel);
        setTitle("Turmas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(480, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        getAll();
        // Vai para janela de criação de turmas
        addBTN.addActionListener(e -> {
            new AddClasses();
            dispose();
        });
        // Vai para janela de atualização de turmas
        updateBTN.addActionListener(e -> {
            try {
                String code = table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString();
                Classes classes = ClassesDAO.getByCode(code);
                new UpdateClasses(classes);
                dispose();
            } catch (ArrayIndexOutOfBoundsException f) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione uma turma.");
                getAll();
                throw new RuntimeException(f);
            } catch (Exception h) {
                throw new RuntimeException(h);
            }
        });
        // Deleta do banco de dados turmas selecionada
        deleteBTN.addActionListener(e -> {
            try {
                String code = table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString();
                // Confirma se o usuário realmente deseja remover o cadastro do aluno.
                int result = JOptionPane.showConfirmDialog(mainPanel,
                        "Você deseja realmente remover o cadastro da turma de código: " + code + "?", null, JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    ClassesDAO.delete(code);
                }
            } catch (ArrayIndexOutOfBoundsException f) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione uma turma.");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            } finally {
                getAll();
            }
        });
        // Pesquisa por aluno pelo seu RA
        searchCodeBTN.addActionListener(e -> {
            try {
                Classes classes = ClassesDAO.getByCode(searchInput.getText());
                if (classes.getCode() == null) {
                    JOptionPane.showMessageDialog(mainPanel, "Nenhuma turma com este código foi encontrada.");
                    getAll();
                } else {
                    Object[][] data;
                    String[] col;
                    col = new String[]{"Código", "UC", "Professor", "Alunos"};
                    data = new Object[1][col.length];
                    data[0][0] = classes.getCode();
                    data[0][1] = UcDAO.getNameByCode(classes.getUc());
                    data[0][2] = ProfessorDAO.getNameByRa(classes.getProfessor());
                    data[0][3] = ClassesDAO.count(classes.getCode());
                    table1.setModel(new DefaultTableModel(data, col));
                    table1.setDefaultEditor(Object.class, null);
                }
            } catch (SQLException f) {
                JOptionPane.showMessageDialog(mainPanel, "SQL ERRROR");
            } catch (Exception g) {
                JOptionPane.showMessageDialog(mainPanel, "Um erro ocorreu");
            } finally {
                searchInput.setText("");
            }
        });
        // Volta para a janela anterior
        backBTN.addActionListener(e -> {
            new MainFrame();
            dispose();
        });
        getAllBTN.addActionListener(e -> getAll());
    }

    // Exibe todos as turmas
    private void getAll() {
        Object[][] data = null;
        String[] col = null;
        try {
            List<Classes> classes;
            classes = ClassesDAO.getAll();
            col = new String[]{"Código", "UC", "Professor", "Alunos"};
            data = new Object[classes.size()][col.length];
            for (int i = 0; i < classes.size(); i++) {
                data[i][0] = classes.get(i).getCode();
                data[i][1] = UcDAO.getNameByCode(classes.get(i).getUc());
                data[i][2] = ProfessorDAO.getNameByRa(classes.get(i).getProfessor());
                data[i][3] = ClassesDAO.count(classes.get(i).getCode());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(mainPanel, "SQL ERRROR");
            System.out.println(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            table1.setModel(new DefaultTableModel(data, col));
            table1.setDefaultEditor(Object.class, null);
        }
    }
}

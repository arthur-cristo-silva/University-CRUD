package com.arthur.frames.classes;

import com.arthur.dao.ClassesDAO;
import com.arthur.entity.Classes;
import com.arthur.excepction.ClassNotFound;
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
    private JButton viewBTN;
    private JButton addStudentsBTN;
    private JButton removeStudentsBTN;
    private JSpinner spinner1;
    private JScrollPane scrollPane;

    // Janela para gerenciar turmas
    public ClassesFrame() {
        setContentPane(mainPanel);
        setTitle("Turmas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        getAll();
        setVisible(true);
        // Vai para janela de criação de turmas
        addBTN.addActionListener(e -> {
            new AddClasses();
            dispose();
        });
        // Vai para janela de atualização de turmas
        updateBTN.addActionListener(e -> {
            try {
                String code = table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString();
                Classes classes = ClassesDAO.getByCodeToUpdt(code);
                new UpdateClasses(classes);
                dispose();
            } catch (ArrayIndexOutOfBoundsException f) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione uma turma.");
            } catch (SQLException f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro ao tentar se conectar com o banco de dados.");
                System.out.println(f);
            } catch (Exception f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro inesperado.");
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
                    getAll();
                }
            } catch (ArrayIndexOutOfBoundsException f) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione uma turma.");
            } catch (SQLException f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro ao tentar se conectar com o banco de dados.");
            } catch (Exception f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro inesperado.");
            }
        });
        // Pesquisa por aluno pelo seu RA
        searchCodeBTN.addActionListener(e -> {
            try {
                Classes classes = ClassesDAO.getByCode(searchInput.getText());
                searchInput.setText("");
                if (classes.getCode() == null) {
                    throw new ClassNotFound();
                }
                String[] col = new String[]{"Código", "UC", "Professor", "Alunos"};
                Object[][] data = new Object[1][col.length];
                data[0][0] = classes.getCode();
                data[0][1] = classes.getUcName() + " " + classes.getUcType();
                data[0][2] = classes.getProfessorName();
                data[0][3] = classes.getAmountOfStudents();
                table1.setModel(new DefaultTableModel(data, col));
                table1.setDefaultEditor(Object.class, null);
            } catch (ClassNotFound f) {
                JOptionPane.showMessageDialog(mainPanel, f);
            } catch (SQLException f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro ao tentar se conectar com o banco de dados.");
            } catch (Exception f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro inesperado.");
            }
        });

        // Exibe todas as turmas
        getAllBTN.addActionListener(e -> getAll());

        // Exibe uma turma
        viewBTN.addActionListener(e -> {
            try {
                String code = table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString();
                String uc = table1.getModel().getValueAt(table1.getSelectedRow(), 1).toString();
                String prof = table1.getModel().getValueAt(table1.getSelectedRow(), 2).toString();
                new ClassesView(code, uc, prof);
                dispose();
            } catch (ArrayIndexOutOfBoundsException f) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione uma turma.");
            } catch (Exception f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro inesperado.");
            }
        });

        addStudentsBTN.addActionListener(e -> {
            try {
                String code = table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString();
                new AddStudents(code);
                dispose();
            } catch (ArrayIndexOutOfBoundsException f) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione uma turma.");
            } catch (Exception f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro inesperado.");
            }
        });
        removeStudentsBTN.addActionListener(e -> {
            try {
                String code = table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString();
                new RemoveStudents(code);
                dispose();
            } catch (ArrayIndexOutOfBoundsException f) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione uma turma.");
            } catch (Exception f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro inesperado.");
            }
        });

        // Volta para a janela anterior
        backBTN.addActionListener(e -> {
            new MainFrame();
            dispose();
        });
    }

    // Exibe todos as turmas
    private void getAll() {
        try {
            List<Classes> classes = ClassesDAO.findAll();
            String[] col = new String[]{"Código", "UC", "Professor", "Alunos"};
            Object[][] data = new Object[classes.size()][col.length];
            for (int i = 0; i < classes.size(); i++) {
                data[i][0] = classes.get(i).getCode();
                data[i][1] = classes.get(i).getUcName() +  " " + classes.get(i).getUcType();
                data[i][2] = classes.get(i).getProfessorName();
                data[i][3] = classes.get(i).getAmountOfStudents();
            }
            table1.setModel(new DefaultTableModel(data, col));
            table1.setDefaultEditor(Object.class, null);
        } catch (SQLException f) {
            JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro ao tentar se conectar com o banco de dados.");
            System.out.println(f.getMessage());
        } catch (Exception f) {
            JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro inesperado.");
        }
    }
}

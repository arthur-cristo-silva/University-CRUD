package com.arthur.frames.students;

import com.arthur.dao.StudentDAO;
import com.arthur.factory.RandomStudent;
import com.arthur.frames.MainFrame;
import com.arthur.entity.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class StudentsFrame extends JFrame {

    private JButton addBTN;
    private JButton updateBTN;
    private JButton deleteBTN;
    private JLabel titleTXT;
    private JButton searchBTN;
    private JPanel mainPanel;
    private JTable table1;
    private JScrollPane jScroll;
    private JTextField raInput;
    private JButton backBTN;
    private JComboBox sortedComboBox;
    private JButton getAllBTN;
    private JButton randomBTN;
    private JButton searchNameBTN;
    private JTextField searchNameInput;
    private JTextField searchInput;
    private JSpinner spinner1;
    private JScrollPane scrollPane;

    // Janela para gerenciar alunos
    public StudentsFrame() {
        setContentPane(mainPanel);
        setTitle("Alunos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
        getAll();
        // Vai para janela de criação de aluno
        addBTN.addActionListener(e -> {
            new AddStudent();
            dispose();
        });
        // Vai para janela de atualização de aluno
        updateBTN.addActionListener(e -> {
            try {
                long ra = Long.parseLong(table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString());
                Student student = StudentDAO.findByRA(String.valueOf(ra));
                new UpdateStudent(student);
                dispose();
            } catch (ArrayIndexOutOfBoundsException f) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione um aluno.");
                getAll();
                throw new RuntimeException(f);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        // Deleta do banco de dados aluno selecionado
        deleteBTN.addActionListener(e -> {
            try {
                long ra = Long.parseLong(table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString());
                // Confirma se o usuário realmente deseja remover o cadastro do aluno.
                int result = JOptionPane.showConfirmDialog(mainPanel,
                        "Você deseja realmente remover o cadastro do aluno de RA: " + ra + "?", null, JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    StudentDAO.delete(ra);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ArrayIndexOutOfBoundsException f) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione um aluno.");
            } finally {
                getAll();
            }
        });
        // Pesquisa por aluno pelo seu RA
        searchBTN.addActionListener(e -> {
            String[] col = null;
            Object[][] data = null;
            try {
                Student student = StudentDAO.findByRA(raInput.getText());
                if (student.getRa() == null) {
                    JOptionPane.showMessageDialog(mainPanel, "Nenhum aluno com este RA foi encontrado.");
                    getAll();
                } else {
                    col = new String[]{"RA", "Nome", "Curso", "Semestre", "Horário", "Faltas"};
                    data = new Object[1][col.length];
                    data[0][0] = student.getRa();
                    data[0][1] = student.getName();
                    data[0][2] = student.getCourse();
                    data[0][3] = student.getPeriod();
                    data[0][4] = student.getSchedule();
                    data[0][5] = student.getAbsences();
                }
            } catch (SQLException f) {
                JOptionPane.showMessageDialog(mainPanel, "Erro ao atualizar aluno.");
            } catch (NumberFormatException g) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, insira dados válidos.");
                getAll();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            } finally {
                table1.setModel(new DefaultTableModel(data, col));
                table1.setDefaultEditor(Object.class, null);
                raInput.setText("");
            }
        });
        // Volta para a janela anterior
        backBTN.addActionListener(e -> {
            new MainFrame();
            dispose();
        });
        getAllBTN.addActionListener(e -> getAll());
        randomBTN.addActionListener(e -> {
            try {
                StudentDAO.save(RandomStudent.getStudent());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            getAll();
        });
        sortedComboBox.addActionListener(e -> getAll());
        searchNameBTN.addActionListener(e -> {
            Object[][] data = null;
            String[] col = null;
            try {
                List<Student> students;
                students = StudentDAO.getByName(searchNameInput.getText());
                col = new String[]{"RA", "Nome", "Curso", "Semestre", "Horário", "Faltas"};
                data = new Object[students.size()][col.length];
                for (int i = 0; i < students.size(); i++) {
                    data[i][0] = students.get(i).getRa();
                    data[i][1] = students.get(i).getName();
                    data[i][2] = students.get(i).getCourse();
                    data[i][3] = students.get(i).getPeriod();
                    data[i][4] = students.get(i).getSchedule();
                    data[i][5] = students.get(i).getAbsences();
                }
            } catch (Exception f) {
                JOptionPane.showMessageDialog(mainPanel, "ERRROR");
            } finally {
                table1.setModel(new DefaultTableModel(data, col));
                table1.setDefaultEditor(Object.class, null);
                searchNameInput.setText("");
            }
        });
    }

    // Exibe todos os alunos
    private void getAll() {
        Object[][] data = null;
        String[] col = null;
        try {
            List<Student> students;
            students = StudentDAO.findAll(sortedComboBox.getSelectedIndex() != 1);
            col = new String[]{"RA", "Nome", "Curso"};
            data = new Object[students.size()][col.length];
            for (int i = 0; i < students.size(); i++) {
                data[i][0] = students.get(i).getRa();
                data[i][1] = students.get(i).getName();
                data[i][2] = students.get(i).getCourse();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(mainPanel, "SQL ERRROR");
        } finally {
            table1.setModel(new DefaultTableModel(data, col));
            table1.setDefaultEditor(Object.class, null);
        }
    }
}

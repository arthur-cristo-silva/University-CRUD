package com.studentscrud.frames.professors;

import com.studentscrud.dao.ProfessorDAO;
import com.studentscrud.dao.StudentDAO;
import com.studentscrud.frames.MainFrame;
import com.studentscrud.objects.Professor;
import com.studentscrud.objects.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ProfessorsFrame extends JFrame {

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
    private JSpinner spinner1;
    private JScrollPane scrollPane;

    public ProfessorsFrame() {
        setContentPane(mainPanel);
        setTitle("Professores");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(480, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        getAll();
        addBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddProfessor();
                dispose();
            }
        });
        updateBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Long ra = Long.valueOf(table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString());
                    String name = table1.getModel().getValueAt(table1.getSelectedRow(), 1).toString();
                    int age = Integer.parseInt(table1.getModel().getValueAt(table1.getSelectedRow(), 2).toString());
                    String email = table1.getModel().getValueAt(table1.getSelectedRow(), 3).toString();
                    int workload = Integer.parseInt(table1.getModel().getValueAt(table1.getSelectedRow(), 4).toString());
                    new UpdateProfessor(ra, name, age, email, workload);
                    dispose();
                } catch (ArrayIndexOutOfBoundsException f) {
                    JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione um professor.");
                    getAll();
                }
            }
        });
        deleteBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Long ra = Long.parseLong(table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString());
                    ProfessorDAO professorDAO = new ProfessorDAO();
                    professorDAO.delete(ra);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ArrayIndexOutOfBoundsException f) {
                    JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione um professor.");
                } finally {
                    getAll();
                }
            }
        });
        searchBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] data = null;
                String[] col = null;
                ProfessorDAO professorDAO = new ProfessorDAO();
                try {
                    Professor professor = professorDAO.findByRA(raInput.getText());
                    if (professor.getRa() == null) {
                        JOptionPane.showMessageDialog(mainPanel, "Nenhum professor com este RA foi encontrado.");
                        getAll();
                    } else {
                        col = new String[]{"RA", "Nome", "Idade", "Email", "CH"};
                        data = new Object[1][col.length];
                        data[0][0] = professor.getRa();
                        data[0][1] = professor.getName();
                        data[0][2] = professor.getAge();
                        data[0][3] = professor.getEmail();
                        data[0][4] = professor.getWorkload();
                        table1.setModel(new DefaultTableModel(data, col));
                        table1.setDefaultEditor(Object.class, null);
                    }
                } catch (SQLException f) {
                    JOptionPane.showMessageDialog(mainPanel, "Erro ao atualizar professor.");
                } catch (NumberFormatException g) {
                    JOptionPane.showMessageDialog(mainPanel, "Por favor, insira dados válidos.");
                    getAll();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                } finally {
                    raInput.setText("");
                }
            }
        });
        backBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainFrame();
                dispose();
            }
        });
    }

    private void getAll() {
        Object[][] data = null;
        String[] col = null;
        try {
            ProfessorDAO professorDAO = new ProfessorDAO();
            List<Professor> professors = professorDAO.findAll();
            col = new String[]{"RA", "Nome", "Idade", "Email", "CH"};
            data = new Object[professors.size()][col.length];
            for (int i = 0; i < professors.size(); i++) {
                data[i][0] = professors.get(i).getRa();
                data[i][1] = professors.get(i).getName();
                data[i][2] = professors.get(i).getAge();
                data[i][3] = professors.get(i).getEmail();
                data[i][4] = professors.get(i).getWorkload();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(mainPanel, "SQL ERRROR");
        } finally {
            table1.setModel(new DefaultTableModel(data, col));
            table1.setDefaultEditor(Object.class, null);
        }

    }
}

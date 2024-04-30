package com.studentscrud.frames;

import com.studentscrud.dao.StudentDAO;
import com.studentscrud.objects.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class MainFrame extends JFrame {

    private JButton addBTN;
    private JButton updateBTN;
    private JButton deleteBTN;
    private JLabel titleTXT;
    private JButton searchBTN;
    private JPanel mainPanel;
    private JTable table1;
    private JScrollPane jScroll;
    private JSpinner spinner1;
    private JScrollPane scrollPane;

    public MainFrame() {

        setContentPane(mainPanel);
        setTitle("CRUD Alunos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(480, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        getAll();
        addBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStudent();
                dispose();
            }
        });
        updateBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Long id = Long.parseLong(table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString());
                    String name = table1.getModel().getValueAt(table1.getSelectedRow(), 1).toString();
                    String ra = table1.getModel().getValueAt(table1.getSelectedRow(), 2).toString();
                    String curso = table1.getModel().getValueAt(table1.getSelectedRow(), 3).toString();
                    String horario = table1.getModel().getValueAt(table1.getSelectedRow(), 4).toString();
                    int faltas = Integer.parseInt(table1.getModel().getValueAt(table1.getSelectedRow(), 5).toString());
                    new UpdateStudent(id, name, ra, curso, horario, faltas);
                    dispose();
                } catch (ArrayIndexOutOfBoundsException f) {
                    JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione um aluno.");
                }
            }
        });
        deleteBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id = Long.parseLong(table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString());
                StudentDAO studentDAO = new StudentDAO();
                try {
                    studentDAO.delete(id);
                    getAll();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        searchBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] data = null;
                String[] col = null;
                StudentDAO studentDAO = new StudentDAO();
                try {
                    Student student = studentDAO.getById(Long.parseLong(spinner1.getValue().toString()));
                    if (student != null) {
                        col = new String[]{"ID", "Nome", "RA", "Curso", "Horário", "Faltas"};
                        data = new Object[1][col.length];
                        data[0][0] = student.getId();
                        data[0][1] = student.getName();
                        data[0][2] = student.getRa();
                        data[0][3] = student.getCurso();
                        data[0][4] = student.getHorario();
                        data[0][5] = student.getFaltas();
                    }
                } catch (SQLException f) {
                    JOptionPane.showMessageDialog(mainPanel, "SQL ERRROR");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                table1.setModel(new DefaultTableModel(data, col));
                table1.setDefaultEditor(Object.class, null);
            }
        });
    }

    private void getAll() {
        Object[][] data = null;
        String[] col = null;
        try {
            StudentDAO studentDAO = new StudentDAO();
            List<Student> students = studentDAO.getAll();
            col = new String[]{"ID", "Nome", "RA", "Curso", "Horário", "Faltas"};
            data = new Object[students.size()][col.length];
            for (int i = 0; i < students.size(); i++) {
                data[i][0] = students.get(i).getId();
                data[i][1] = students.get(i).getName();
                data[i][2] = students.get(i).getRa();
                data[i][3] = students.get(i).getCurso();
                data[i][4] = students.get(i).getHorario();
                data[i][5] = students.get(i).getFaltas();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(mainPanel, "SQL ERRROR");
        }
        table1.setModel(new DefaultTableModel(data, col));
        table1.setDefaultEditor(Object.class, null);
    }
}

package com.arthur.frames.students;

import com.arthur.dao.StudentDAO;
import com.arthur.frames.MainFrame;
import com.arthur.entity.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private JSpinner spinner1;
    private JScrollPane scrollPane;

    public StudentsFrame() {
        setContentPane(mainPanel);
        setTitle("Alunos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(480, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        getAll();
        addBTN.addActionListener(e -> {
            new AddStudent();
            dispose();
        });
        updateBTN.addActionListener(e -> {
            try {
                getAll();
                Long ra = Long.valueOf(table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString());
                String name = table1.getModel().getValueAt(table1.getSelectedRow(), 1).toString();
                String course = table1.getModel().getValueAt(table1.getSelectedRow(), 2).toString();
                int period = Integer.parseInt(table1.getModel().getValueAt(table1.getSelectedRow(), 3).toString());
                String schedule = table1.getModel().getValueAt(table1.getSelectedRow(), 4).toString();
                int absences = Integer.parseInt(table1.getModel().getValueAt(table1.getSelectedRow(), 5).toString());
                new UpdateStudent(ra, name, course, period, schedule, absences);
                dispose();
            } catch (ArrayIndexOutOfBoundsException f) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione um aluno.");
                getAll();
            }
        });
        deleteBTN.addActionListener(e -> {
            try {
                Long ra = Long.parseLong(table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString());
                new StudentDAO().delete(ra);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ArrayIndexOutOfBoundsException f) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione um aluno.");
            } finally {
                getAll();
            }
        });
        searchBTN.addActionListener(e -> {
            Object[][] data = null;
            String[] col = null;
            try {
                Student student = new StudentDAO().findByRA(raInput.getText());
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
                    table1.setModel(new DefaultTableModel(data, col));
                    table1.setDefaultEditor(Object.class, null);
                }
            } catch (SQLException f) {
                JOptionPane.showMessageDialog(mainPanel, "Erro ao atualizar aluno.");
            } catch (NumberFormatException g) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, insira dados válidos.");
                getAll();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            } finally {
                raInput.setText("");
            }
        });
        backBTN.addActionListener(e -> {
            new MainFrame();
            dispose();
        });
    }

    private void getAll() {
        Object[][] data = null;
        String[] col = null;
        try {
            List<Student> students = new StudentDAO().findAll();
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

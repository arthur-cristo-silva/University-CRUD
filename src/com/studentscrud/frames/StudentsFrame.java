package com.studentscrud.frames;

import com.studentscrud.dao.StudentDAO;
import com.studentscrud.objects.Student;

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
                    Long ra = Long.valueOf(table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString());
                    String name = table1.getModel().getValueAt(table1.getSelectedRow(), 1).toString();
                    int age = Integer.parseInt(table1.getModel().getValueAt(table1.getSelectedRow(), 2).toString());
                    String course = table1.getModel().getValueAt(table1.getSelectedRow(), 3).toString();
                    String schedule = table1.getModel().getValueAt(table1.getSelectedRow(), 4).toString();
                    int absences = Integer.parseInt(table1.getModel().getValueAt(table1.getSelectedRow(), 5).toString());
                    new UpdateStudent(ra, name, age, course, schedule, absences);
                    dispose();
                } catch (ArrayIndexOutOfBoundsException f) {
                    JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione um aluno.");
                    getAll();
                }
            }
        });
        deleteBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Long ra = Long.parseLong(table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString());
                    StudentDAO studentDAO = new StudentDAO();
                    studentDAO.delete(ra);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ArrayIndexOutOfBoundsException f) {
                    JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione um aluno.");
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
                StudentDAO studentDAO = new StudentDAO();
                try {
                    Student student = studentDAO.findByRA(raInput.getText());
                    if (student.getRa() == null) {
                        JOptionPane.showMessageDialog(mainPanel, "Nenhum aluno com este RA foi encontrado.");
                        getAll();
                    } else {
                        col = new String[]{"RA", "Nome", "Idade", "Curso", "Horário", "Faltas"};
                        data = new Object[1][col.length];
                        data[0][0] = student.getRa();
                        data[0][1] = student.getName();
                        data[0][2] = student.getAge();
                        data[0][3] = student.getCourse();
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
            StudentDAO studentDAO = new StudentDAO();
            List<Student> students = studentDAO.findAll();
            col = new String[]{"RA", "Nome", "Idade", "Curso", "Horário", "Faltas"};
            data = new Object[students.size()][col.length];
            for (int i = 0; i < students.size(); i++) {
                data[i][0] = students.get(i).getRa();
                data[i][1] = students.get(i).getName();
                data[i][2] = students.get(i).getAge();
                data[i][3] = students.get(i).getCourse();
                data[i][4] = students.get(i).getSchedule();
                data[i][5] = students.get(i).getAbsences();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(mainPanel, "SQL ERRROR");
        } finally {
            table1.setModel(new DefaultTableModel(data, col));
            table1.setDefaultEditor(Object.class, null);
        }

    }
}

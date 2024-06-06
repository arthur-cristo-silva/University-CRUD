package com.arthur.frames.classes;

import com.arthur.dao.ClassesDAO;
import com.arthur.dao.StudentDAO;
import com.arthur.entity.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class AddStudents extends JFrame {
    private JPanel mainPanel;
    private JLabel titleTXT;
    private JButton addBTN;
    private JTable table1;
    private JButton backBTN;
    private JScrollPane jScroll;

    public AddStudents(String code) {
        setContentPane(mainPanel);
        setTitle("Adicionar Aluno");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
        getAll(code);
        addBTN.addActionListener(e -> {
            try {
                ClassesDAO.addStudent(code, Long.parseLong(table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString()));
                JOptionPane.showMessageDialog(mainPanel, "Aluno adicionado!");
                getAll(code);
            } catch (SQLException f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro ao tentar se conectar com o banco de dados.");
                System.out.println(f);
            }
        });
        backBTN.addActionListener(e -> {
            new ClassesFrame();
            dispose();
        });
    }
    private void getAll(String code) {
        List<Student> students;
        try {
            students = StudentDAO.getStudentsClass(code, true);
            String[] col = new String[]{"RA", "Nome", "Curso"};
            Object[][] data = new Object[students.size()][col.length];
            for (int i = 0; i < students.size(); i++) {
                data[i][0] = students.get(i).getRa();
                data[i][1] = students.get(i).getName();
                data[i][2] = students.get(i).getCourse();
            }
            table1.setModel(new DefaultTableModel(data, col));
            table1.setDefaultEditor(Object.class, null);
        } catch (SQLException f) {
            JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro ao tentar se conectar com o banco de dados.");
        } catch (Exception f) {
            JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro inesperado.");
        }
    }
}

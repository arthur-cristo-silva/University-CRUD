package com.arthur.frames.classes;

import com.arthur.dao.ProfessorDAO;
import com.arthur.dao.StudentDAO;
import com.arthur.dao.UcDAO;
import com.arthur.entity.Classes;
import com.arthur.entity.Professor;
import com.arthur.entity.Student;
import com.arthur.excepction.ProfessorNotFound;
import com.arthur.factory.RandomProfessor;
import com.arthur.frames.MainFrame;
import com.arthur.frames.professors.AddProfessor;
import com.arthur.frames.professors.UpdateProfessor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class ClassesView extends JFrame {

    private JPanel mainPanel;
    private JTable table1;
    private JScrollPane jScroll;
    private JButton backBTN;
    private JLabel ucTXT;
    private JLabel profTXT;

    public ClassesView(String code, String uc, String prof) {
        setContentPane(mainPanel);
        setTitle("Turma " + code);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        ucTXT.setText(uc);
        profTXT.setText("Professor(a): " + prof);
        getAll(code);
        setVisible(true);
        backBTN.addActionListener(e -> {
            new ClassesFrame();
            dispose();
        });
    }

    private void getAll(String code) {
        List<Student> students;
        try {
            students = StudentDAO.getStudentsClass(code, false);
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

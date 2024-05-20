package com.arthur.frames.students;

import com.arthur.dao.StudentDAO;
import com.arthur.entity.Student;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Objects;

public class UpdateStudent  extends JFrame {

    // Elements
    private JLabel titleTXT;
    private JTextField nameInput;
    private JTextField periodInput;
    private JTextField absencesInput;
    private JComboBox scheduleInput;
    private JTextField courseInput;
    private JButton addBTN;
    private JButton backButton;
    private JPanel mainPanel;
    private JLabel nameTXT;
    private JLabel cursoTXT;
    private JLabel horarioTXT;
    private JLabel faltasTXT;
    private JLabel periodTXT;

    public UpdateStudent(Long ra, String name, String course, int period, String schedule, int absences) {
        setContentPane(mainPanel);
        setTitle("Atualizar Aluno");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        nameInput.setText(name);
        courseInput.setText(course);
        periodInput.setText(String.valueOf(period));
        scheduleInput.setSelectedItem(schedule);
        absencesInput.setText(String.valueOf(absences));
        addBTN.addActionListener(e -> {
            try {
                Student student = getStudent(ra);
                StudentDAO studentDAO = new StudentDAO();
                studentDAO.update(student);
                JOptionPane.showMessageDialog(mainPanel, "Aluno atualizado!");
                new StudentsFrame();
                dispose();
            } catch (SQLException g) {
                JOptionPane.showMessageDialog(mainPanel, "Erro ao atualizar aluno no banco de dados.");
            } catch (Exception h) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, insira dados válidos.");
                throw new RuntimeException(h);
            }
        });
        backButton.addActionListener(e -> {
            new StudentsFrame();
            dispose();
        });
    }

    private Student getStudent(Long ra) throws Exception {
        String name = nameInput.getText();
        int period = Objects.equals(periodInput.getText(), "") ? 1 : Integer.parseInt(periodInput.getText());
        if (period == 0) {
            period = 1;
        }
        String course = courseInput.getText();
        String schedule = Objects.requireNonNull(scheduleInput.getSelectedItem()).toString();
        int absences = Objects.equals(absencesInput.getText(), "") ? 0 : Integer.parseInt(absencesInput.getText());
        if (name.isEmpty() || course.isEmpty()) {
            throw new Exception();
        }
        return new Student(ra, name, course, period, schedule, absences);
    }
}
package com.studentscrud.frames.students;

import com.studentscrud.dao.StudentDAO;
import com.studentscrud.objects.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

public class AddStudent extends JFrame {

    // Elements
    private JLabel titleTXT;
    private JTextField nameInput;
    private JTextField ageInput;
    private JTextField absencesInput;
    private JComboBox scheduleInput;
    private JTextField courseInput;
    private JLabel nameTXT;
    private JLabel courseTXT;
    private JLabel ageTXT;
    private JLabel scheduleTXT;
    private JLabel absencesTXT;
    private JButton addBTN;
    private JButton backButton;
    private JPanel mainPanel;

    public AddStudent() {
        setContentPane(mainPanel);
        setTitle("Adicionar Novo Aluno");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        addBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameInput.getText();
                    int age = Objects.equals(ageInput.getText(), "") ? 0 : Integer.parseInt(ageInput.getText());
                    String course = courseInput.getText();
                    String schedule = Objects.requireNonNull(scheduleInput.getSelectedItem()).toString();
                    int absences = Objects.equals(absencesInput.getText(), "") ? 0 : Integer.parseInt(absencesInput.getText());
                    if (name.isEmpty() || age == 0 || course.isEmpty() || schedule.isEmpty()) {
                        throw new Exception();
                    }
                    Student student = new Student(name, age, course, schedule, absences);
                    StudentDAO studentDAO = new StudentDAO();
                    studentDAO.save(student);
                    JOptionPane.showMessageDialog(mainPanel, "Aluno adicionado!");
                    new StudentsFrame();
                    dispose();
                } catch (SQLException g) {
                    JOptionPane.showMessageDialog(mainPanel, "Erro ao salvar aluno no banco de dados.");
                } catch (Exception h) {
                    JOptionPane.showMessageDialog(mainPanel, "Por favor, insira dados válidos.");
                    throw new RuntimeException(h);
                }
            }
        });
        backButton.addActionListener(e -> {
            new StudentsFrame();
            dispose();
        });
    }
}
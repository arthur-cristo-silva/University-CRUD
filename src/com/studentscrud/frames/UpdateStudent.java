package com.studentscrud.frames;

import com.studentscrud.dao.StudentDAO;
import com.studentscrud.objects.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

public class UpdateStudent  extends JFrame {

    // Elements
    private JLabel titleTXT;
    private JTextField nameInput;
    private JTextField ageInput;
    private JTextField faltasInput;
    private JComboBox horarioInput;
    private JTextField cursoInput;
    private JLabel nameTXT;
    private JLabel cursoTXT;
    private JLabel ageTXT;
    private JLabel horarioTXT;
    private JLabel faltasTXT;
    private JButton addBTN;
    private JButton backButton;
    private JPanel mainPanel;
    private JTextField idInput;

    public UpdateStudent(Long ra, String name, int age, String course, String schedule, int absences) {
        setContentPane(mainPanel);
        setTitle("Atualizar Aluno");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        nameInput.setText(name);
        ageInput.setText(String.valueOf(age));
        cursoInput.setText(course);
        horarioInput.setSelectedItem(schedule);
        faltasInput.setText(String.valueOf(absences));
        addBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nameIN = nameInput.getText();
                    int ageIN = Objects.equals(ageInput.getText(), "") ? 0 : Integer.parseInt(ageInput.getText());
                    String courseIN = cursoInput.getText();
                    String scheduleIN = Objects.requireNonNull(horarioInput.getSelectedItem()).toString();
                    int absencesIN = Objects.equals(faltasInput.getText(), "") ? 0 : Integer.parseInt(faltasInput.getText());
                    if (nameIN.isEmpty() || ageIN == 0 || courseIN.isEmpty() || scheduleIN.isEmpty()) {
                        throw new Exception();
                    }
                    Student student = new Student(ra, nameIN, ageIN, courseIN, scheduleIN, absencesIN);
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
            }
        });
        backButton.addActionListener(e -> {
            new StudentsFrame();
            dispose();
        });
    }
}
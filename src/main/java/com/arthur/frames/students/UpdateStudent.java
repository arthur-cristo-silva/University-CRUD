package com.arthur.frames.students;

import com.arthur.dao.StudentDAO;
import com.arthur.entity.Student;

import javax.swing.*;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Objects;

public class UpdateStudent  extends JFrame {

    // Elementos da janela
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

    // Atualiza informações de estudante do banco de dados
    public UpdateStudent(Student student) {
        setContentPane(mainPanel);
        setTitle("Atualizar Aluno");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        // Preenche nos inputs o nome e curso do aluno
        nameInput.setText(student.getName());
        courseInput.setText(student.getCourse());
        periodInput.setText(student.getPeriod().toString());
        absencesInput.setText(student.getAbsences().toString());
        scheduleInput.setSelectedItem(student.getSchedule());
        addBTN.addActionListener(e -> {
            try {
                String name = nameInput.getText();
                int period = Objects.equals(periodInput.getText(), "") ? 1 : Integer.parseInt(periodInput.getText());
                String course = courseInput.getText();
                String schedule = Objects.requireNonNull(scheduleInput.getSelectedItem()).toString();
                int absences = Objects.equals(absencesInput.getText(), "") ? 0 : Integer.parseInt(absencesInput.getText());
                StudentDAO.update(Student.getStudent(student.getRa(), name, course, period, schedule, absences));
                JOptionPane.showMessageDialog(mainPanel, "Aluno atualizado!");
                new StudentsFrame();
                dispose();
            } catch (SQLException f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro ao tentar se conectar com o banco de dados.");
            } catch (InputMismatchException f) {
                JOptionPane.showMessageDialog(mainPanel, f.getMessage());
            } catch (Exception f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro inesperado.");
            }
        });
        // Volta para janela anterior
        backButton.addActionListener(e -> {
            new StudentsFrame();
            dispose();
        });
    }
}
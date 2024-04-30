package com.studentscrud.frames;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
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
    private JTextField raInput;
    private JTextField faltasInput;
    private JComboBox horarioInput;
    private JTextField cursoInput;
    private JLabel nameTXT;
    private JLabel cursoTXT;
    private JLabel raTXT;
    private JLabel horarioTXT;
    private JLabel faltasTXT;
    private JButton addBTN;
    private JButton backButton;
    private JPanel mainPanel;

    // Variables
    private String name;
    private String ra;
    private String curso;
    private String horario;
    private int faltas;

    public AddStudent() {
        setContentPane(mainPanel);
        setTitle("Adicionar Novo Aluno");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 350);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        addBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    name = nameInput.getText();
                    ra = raInput.getText();
                    curso = cursoInput.getText();
                    horario = horarioInput.getSelectedItem().toString();
                    faltas = Objects.equals(faltasInput.getText(), "") ? 0 : Integer.parseInt(faltasInput.getText());
                    if (name.isEmpty() || ra.isEmpty() || curso.isEmpty() || horario.isEmpty()) {
                        throw new Exception();
                    }
                    Student student = new Student(name, ra, curso, horario, faltas);
                    StudentDAO studentDAO = new StudentDAO();
                    studentDAO.save(student);
                    JOptionPane.showMessageDialog(mainPanel, "Aluno adicionado!");
                    new MainFrame();
                    dispose();
                } catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(mainPanel, "Por favor, insira apenas números em faltas.");
                } catch (SQLException g) {
                    JOptionPane.showMessageDialog(mainPanel, "Erro ao salvar aluno no banco de dados.");
                } catch (Exception h) {
                    JOptionPane.showMessageDialog(mainPanel, "Por favor, insira dados válidos.");
                }
            }
        });
        backButton.addActionListener(e -> {
            new MainFrame();
            dispose();
        });
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }
}
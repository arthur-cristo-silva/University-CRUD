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
    private JTextField idInput;
    private JLabel idTXT;

    // Variables
    private String name;
    private String ra;
    private String curso;
    private String horario;
    private int faltas;
    private Long id;

    public UpdateStudent(Long id, String name, String ra, String curso, String horario, int faltas) {
        this.id = id;
        this.name = name;
        this.ra = ra;
        this.curso = curso;
        this.horario = horario;
        this.faltas = faltas;
        setContentPane(mainPanel);
        setTitle("Atualizar Aluno");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 350);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        idInput.setText(String.valueOf(id));
        nameInput.setText(name);
        raInput.setText(ra);
        cursoInput.setText(curso);
        horarioInput.setSelectedItem(horario);
        faltasInput.setText(String.valueOf(faltas));
        addBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Long idIN = Long.parseLong(idInput.getText());
                    String nameIN = nameInput.getText();
                    String raIN = raInput.getText();
                    String cursoIN = cursoInput.getText();
                    String horarioIN = horarioInput.getSelectedItem().toString();
                    int faltasIN = Objects.equals(faltasInput.getText(), "") ? 0 : Integer.parseInt(faltasInput.getText());
                    Student student = new Student(idIN, nameIN, raIN, cursoIN, horarioIN, faltasIN);
                    StudentDAO studentDAO = new StudentDAO();
                    studentDAO.update(student);
                    JOptionPane.showMessageDialog(mainPanel, "Aluno atualizado!");
                    new MainFrame();
                    dispose();
                } catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(mainPanel, "Por favor, insira apenas números em faltas.");
                } catch (Exception g) {
                    JOptionPane.showMessageDialog(mainPanel, "Por favor, insira apenas dados válidos.");
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
package com.studentscrud.frames;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class UpdateStudent extends JFrame {

    private JLabel titleTXT;
    private JTextField nameInput;
    private JTextField raInput;
    private JTextField cursoInput;
    private JComboBox horarioInput;
    private JTextField faltasInput;
    private JLabel nameTXT;
    private JLabel raTXT;
    private JLabel cursoTXT;
    private JLabel horarioTXT;
    private JLabel faltasTXT;
    private JButton updateBTN;
    private JButton backButton;
    private JPanel mainPanel;

    // Variables
    private String name;
    private String ra;
    private String curso;
    private String horario;
    private int faltas;

    public UpdateStudent() {
        setContentPane(mainPanel);
        setTitle("Update student");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(720, 720);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        updateBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setName(nameInput.getText());
                    setRa(raInput.getText());
                    setCurso(cursoInput.getText());
                    setHorario(horarioInput.getSelectedItem().toString());
                    if (Objects.equals(faltasInput.getText(), "")) {
                        setFaltas(0);
                    } else {
                        setFaltas(Integer.parseInt(faltasInput.getText()));
                    }
                    String student = String.format("Nome: %s%nRA: %s%nCurso: %s%nHorário: %s%nFaltas: %d",
                            getName(), getRa(), getCurso(), getHorario(), getFaltas());
                    JOptionPane.showMessageDialog(mainPanel, student);
                    new MainFrame();
                    dispose();
                } catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(mainPanel, "Por favor, insira apenas números em faltas.");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainFrame();
                dispose();
            }
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

package com.studentscrud.frames;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private JButton addBTN;
    private JButton updateBTN;
    private JButton deleteBTN;
    private JLabel titleTXT;
    private JButton searchBTN;
    private JComboBox searchInput;
    private JPanel mainPanel;
    private JTable table1;

    public MainFrame() {
        setContentPane(mainPanel);
        setTitle("CRUD Students");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(720, 720);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

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
                new UpdateStudent();
                dispose();
            }
        });
    }
}

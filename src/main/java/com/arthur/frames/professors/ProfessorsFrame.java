package com.arthur.frames.professors;

import com.arthur.dao.ProfessorDAO;
import com.arthur.excepction.ProfessorNotFound;
import com.arthur.factory.RandomProfessor;
import com.arthur.frames.MainFrame;
import com.arthur.entity.Professor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class ProfessorsFrame extends JFrame {

    private JButton addBTN;
    private JButton updateBTN;
    private JButton deleteBTN;
    private JLabel titleTXT;
    private JButton searchBTN;
    private JPanel mainPanel;
    private JTable table1;
    private JScrollPane jScroll;
    private JTextField raInput;
    private JButton backBTN;
    private JButton getAllBTN;
    private JButton randomBTN;
    private JComboBox<String> sortedComboBox;
    private JTextField searchNameInput;
    private JButton searchNameBTN;
    private JSpinner spinner1;
    private JScrollPane scrollPane;

    // Janela para gerenciar professores
    public ProfessorsFrame() {
        setContentPane(mainPanel);
        setTitle("Professores");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        getAll();
        setVisible(true);
        // Vai para janela de criação de professor
        addBTN.addActionListener(e -> {
            new AddProfessor();
            dispose();
        });
        // Vai para janela de atualização de professor
        updateBTN.addActionListener(e -> {
            try {
                long ra = Long.parseLong(table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString());
                Professor professor = ProfessorDAO.findByRA(String.valueOf(ra));
                new UpdateProfessor(professor);
                dispose();
            } catch (ArrayIndexOutOfBoundsException f) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione um professor.");
            } catch (SQLException f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro ao tentar se conectar com o banco de dados.");
            }
        });
        // Deleta do banco de dados professor selecionado
        deleteBTN.addActionListener(e -> {
            try {
                long ra = Long.parseLong(table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString());
                // Confirma se o usuário realmente deseja remover o cadastro do aluno.
                int result = JOptionPane.showConfirmDialog(mainPanel,
                        "Você deseja realmente remover o cadastro do professor de RA: " + ra + "?", null, JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    ProfessorDAO.delete(ra);
                    getAll();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro ao tentar se conectar com o banco de dados.");
            } catch (ArrayIndexOutOfBoundsException f) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione um professor.");
            }
        });

        // Pesquisa por professor pelo seu RA
        searchBTN.addActionListener(e -> {
            try {
                Professor professor = ProfessorDAO.findByRA(raInput.getText());
                raInput.setText("");
                if (professor.getRa() == null) {
                    throw new ProfessorNotFound();
                }
                String[] col = new String[]{"RA", "Nome", "E-mail", "Telefone", "Carga Horária"};
                Object[][] data = new Object[1][col.length];
                data[0][0] = professor.getRa();
                data[0][1] = professor.getName();
                data[0][2] = professor.getEmail();
                data[0][3] = professor.getPhoneNumber();
                data[0][4] = professor.getWorkload();
                table1.setModel(new DefaultTableModel(data, col));
                table1.setDefaultEditor(Object.class, null);
            } catch (SQLException f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro ao tentar se conectar com o banco de dados.");
            } catch (NumberFormatException g) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, insira um RA válido.");
            } catch (ProfessorNotFound h) {
                JOptionPane.showMessageDialog(mainPanel, h);
            }
        });

        // Pesquisa por professor pelo seu nome
        searchNameBTN.addActionListener(e -> {
            try {
                List<Professor> professors;
                professors = ProfessorDAO.getByName(searchNameInput.getText());
                if (professors.isEmpty()) {
                    throw new ProfessorNotFound();
                }
                searchNameInput.setText("");
                String[] col = new String[]{"RA", "Nome", "E-mail", "Telefone", "Carga Horária"};
                Object[][] data = new Object[professors.size()][col.length];
                for (int i = 0; i < professors.size(); i++) {
                    data[i][0] = professors.get(i).getRa();
                    data[i][1] = professors.get(i).getName();
                    data[i][2] = professors.get(i).getEmail();
                    data[i][3] = professors.get(i).getPhoneNumber();
                    data[i][4] = professors.get(i).getWorkload();
                }
                table1.setModel(new DefaultTableModel(data, col));
                table1.setDefaultEditor(Object.class, null);
            } catch (SQLException f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro ao tentar se conectar com o banco de dados.");
            } catch (ProfessorNotFound g) {
                JOptionPane.showMessageDialog(mainPanel, g);
            } catch (Exception h) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro inesperado.");
            }
        });

        // Exibe todos os professores
        getAllBTN.addActionListener(e -> getAll());

        // Cadastra professor ficticio
        randomBTN.addActionListener(e -> {
            try {
                ProfessorDAO.save(RandomProfessor.getProfessor());
                getAll();
            } catch (SQLException f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro ao tentar se conectar com o banco de dados.");
            }
        });

        // Ordena professores por RA ou Nome
        sortedComboBox.addActionListener(e -> getAll());

        // Volta para a janela anterior
        backBTN.addActionListener(e -> {
            new MainFrame();
            dispose();
        });
    }

    // Exibe todos os professores
    private void getAll() {
        try {
            List<Professor> professors;
            professors = ProfessorDAO.findAll(sortedComboBox.getSelectedIndex() != 1);
            String[] col = new String[]{"RA", "Nome", "Email"};
            Object[][] data = new Object[professors.size()][col.length];
            for (int i = 0; i < professors.size(); i++) {
                data[i][0] = professors.get(i).getRa();
                data[i][1] = professors.get(i).getName();
                data[i][2] = professors.get(i).getEmail();
            }
            table1.setModel(new DefaultTableModel(data, col));
            table1.setDefaultEditor(Object.class, null);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro ao tentar se conectar com o banco de dados.");
        }
    }
}

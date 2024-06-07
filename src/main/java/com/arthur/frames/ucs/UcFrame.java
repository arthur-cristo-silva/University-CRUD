package com.arthur.frames.ucs;

import com.arthur.dao.UcDAO;
import com.arthur.entity.Uc;
import com.arthur.excepction.UcNotFound;
import com.arthur.factory.RandomUc;
import com.arthur.frames.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;

public class UcFrame extends JFrame {

    private JButton addBTN;
    private JButton updateBTN;
    private JButton deleteBTN;
    private JLabel titleTXT;
    private JButton searchCodeBTN;
    private JPanel mainPanel;
    private JTable table1;
    private JScrollPane jScroll;
    private JButton backBTN;
    private JButton getAllBTN;
    private JButton randomBTN;
    private JTextField searchInput;
    private JButton searchNameBTN;
    private JTextField searchNameInput;
    private JSpinner spinner1;
    private JScrollPane scrollPane;

    // Janela para gerenciar alunos
    public UcFrame() {
        setContentPane(mainPanel);
        setTitle("Unidades Curriculares");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        getAll();
        setVisible(true);
        // Vai para janela de criação de UC
        addBTN.addActionListener(e -> {
            new AddUc();
            dispose();
        });
        // Vai para janela de atualização de UC
        updateBTN.addActionListener(e -> {
            try {
                String code = table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString();
                new UpdateUc(UcDAO.getByCode(code));
                dispose();
            } catch (ArrayIndexOutOfBoundsException f) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione uma UC.");
                getAll();
            } catch (Exception h) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro inesperado.");
            }
        });
        // Deleta do banco de dados UC selecionada
        deleteBTN.addActionListener(e -> {
            try {
                String code = table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString();
                // Confirma se o usuário realmente deseja remover o cadastro do aluno.
                int result = JOptionPane.showConfirmDialog(mainPanel,
                        "Você deseja realmente remover o cadastro da UC de código: " + code + "?", null, JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    UcDAO.delete(code);
                    getAll();
                }
            } catch (ArrayIndexOutOfBoundsException f) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione uma UC.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro inesperado.");
            }
        });
        // Pesquisa por aluno pelo seu RA
        searchCodeBTN.addActionListener(e -> {
            try {
                Uc uc = UcDAO.getByCode(searchInput.getText());
                searchInput.setText("");
                if (uc.getCode() == null) {
                    throw new UcNotFound();
                }
                String[] col = new String[]{"Código", "Nome", "Tipo"};
                Object[][] data = new Object[1][col.length];
                data[0][0] = uc.getCode();
                data[0][1] = uc.getName();
                data[0][2] = uc.getType();
                table1.setModel(new DefaultTableModel(data, col));
                table1.setDefaultEditor(Object.class, null);
            } catch (SQLException f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro ao tentar se conectar com o banco de dados.");
            } catch (InputMismatchException f) {
                JOptionPane.showMessageDialog(mainPanel, f.getMessage());
            } catch (Exception f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro inesperado.");
            }
        });

        // Exibe todas as UCs
        getAllBTN.addActionListener(e -> getAll());

        // Cadastra UC ficticia
        randomBTN.addActionListener(e -> {
            try {
                UcDAO.save(RandomUc.getUc());
                getAll();
            } catch (SQLException f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro ao tentar se conectar com o banco de dados.");
            }
        });
        searchNameBTN.addActionListener(e -> {
            try {
                List<Uc> ucs;
                ucs = UcDAO.getByName(searchNameInput.getText());
                searchNameInput.setText("");
                if (ucs.isEmpty()) {
                    throw new UcNotFound();
                }
                String[] col = new String[]{"Código", "Nome", "Tipo"};
                Object[][] data = new Object[ucs.size()][col.length];
                for (int i = 0; i < ucs.size(); i++) {
                    data[i][0] = ucs.get(i).getCode();
                    data[i][1] = ucs.get(i).getName();
                    data[i][2] = ucs.get(i).getType();
                }
                table1.setModel(new DefaultTableModel(data, col));
                table1.setDefaultEditor(Object.class, null);
            } catch (SQLException f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro ao tentar se conectar com o banco de dados.");
            } catch (UcNotFound f) {
                JOptionPane.showMessageDialog(mainPanel, f.getMessage());
            } catch (Exception f) {
                JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro inesperado.");
            }
        });
        // Volta para a janela anterior
        backBTN.addActionListener(e -> {
            new MainFrame();
            dispose();
        });
    }

    // Exibe todos os alunos
    private void getAll() {
        try {
            List<Uc> ucs;
            ucs = UcDAO.getAll();
            String[] col = new String[]{"Código", "Nome", "Tipo"};
            Object[][] data = new Object[ucs.size()][col.length];
            for (int i = 0; i < ucs.size(); i++) {
                data[i][0] = ucs.get(i).getCode();
                data[i][1] = ucs.get(i).getName();
                data[i][2] = ucs.get(i).getType();
            }
            table1.setModel(new DefaultTableModel(data, col));
            table1.setDefaultEditor(Object.class, null);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(mainPanel, "Desculpe, ocorreu um erro ao tentar se conectar com o banco de dados.");
        }
    }
}

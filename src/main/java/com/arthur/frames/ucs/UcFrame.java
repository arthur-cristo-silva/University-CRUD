package com.arthur.frames.ucs;

import com.arthur.dao.UcDAO;
import com.arthur.entity.Uc;
import com.arthur.factory.RandomUc;
import com.arthur.frames.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
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
        setSize(480, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        getAll();
        // Vai para janela de criação de UC
        addBTN.addActionListener(e -> {
            new AddUc();
            dispose();
        });
        // Vai para janela de atualização de UC
        updateBTN.addActionListener(e -> {
            try {
                String code = table1.getModel().getValueAt(table1.getSelectedRow(), 0).toString();
                Uc uc = UcDAO.getByCode(code);
                new UpdateUc(uc);
                dispose();
            } catch (ArrayIndexOutOfBoundsException f) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione uma UC.");
                getAll();
                throw new RuntimeException(f);
            } catch (Exception h) {
                throw new RuntimeException(h);
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
                }
            } catch (ArrayIndexOutOfBoundsException f) {
                JOptionPane.showMessageDialog(mainPanel, "Por favor, selecione uma UC.");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            } finally {
                getAll();
            }
        });
        // Pesquisa por aluno pelo seu RA
        searchCodeBTN.addActionListener(e -> {
            try {
                Uc uc = UcDAO.getByCode(searchInput.getText());
                if (uc.getCode() == null) {
                    JOptionPane.showMessageDialog(mainPanel, "Nenhuma UC com este código foi encontrada.");
                    getAll();
                } else {
                    Object[][] data;
                    String[] col;
                    col = new String[]{"Código", "Nome", "Tipo"};
                    data = new Object[1][col.length];
                    data[0][0] = uc.getCode();
                    data[0][1] = uc.getName();
                    data[0][2] = uc.getType();
                    table1.setModel(new DefaultTableModel(data, col));
                    table1.setDefaultEditor(Object.class, null);
                }
            } catch (SQLException f) {
                JOptionPane.showMessageDialog(mainPanel, "SQL ERRROR");
            } catch (Exception g) {
                JOptionPane.showMessageDialog(mainPanel, "Um erro ocorreu");
            } finally {
                searchInput.setText("");
            }
        });
        // Volta para a janela anterior
        backBTN.addActionListener(e -> {
            new MainFrame();
            dispose();
        });
        getAllBTN.addActionListener(e -> getAll());
        randomBTN.addActionListener(e -> {
            try {
                UcDAO.save(RandomUc.getUc());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            getAll();
        });
        searchNameBTN.addActionListener(e -> {
            Object[][] data = null;
            String[] col = null;
            try {
                List<Uc> ucs;
                ucs = UcDAO.getByName(searchNameInput.getText());
                col = new String[]{"Código", "Nome", "Tipo"};
                data = new Object[ucs.size()][col.length];
                for (int i = 0; i < ucs.size(); i++) {
                    data[i][0] = ucs.get(i).getCode();
                    data[i][1] = ucs.get(i).getName();
                    data[i][2] = ucs.get(i).getType();
                }
            } catch (SQLException f) {
                JOptionPane.showMessageDialog(mainPanel, "SQL ERRROR");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            } finally {
                table1.setModel(new DefaultTableModel(data, col));
                table1.setDefaultEditor(Object.class, null);
            }
        });
    }

    // Exibe todos os alunos
    private void getAll() {
        Object[][] data = null;
        String[] col = null;
        try {
            List<Uc> ucs;
            ucs = UcDAO.getAll();
            col = new String[]{"Código", "Nome", "Tipo"};
            data = new Object[ucs.size()][col.length];
            for (int i = 0; i < ucs.size(); i++) {
                data[i][0] = ucs.get(i).getCode();
                data[i][1] = ucs.get(i).getName();
                data[i][2] = ucs.get(i).getType();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(mainPanel, "SQL ERRROR");
        } finally {
            table1.setModel(new DefaultTableModel(data, col));
            table1.setDefaultEditor(Object.class, null);
        }
    }
}

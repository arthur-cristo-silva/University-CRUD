package com.arthur;

import com.arthur.frames.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            new MainFrame();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Desculpe, ocorreu um erro inesperado.");
        }
    }
}

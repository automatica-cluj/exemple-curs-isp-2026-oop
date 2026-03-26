package utcluj.aut;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExempluSwing extends JFrame {
    private JTextField textField;
    private JButton buton;

    public ExempluSwing() {
        // Configurare fereastră
        setTitle("Exemplu Swing Simplu");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creare componente
        textField = new JTextField(15);
        buton = new JButton("Apasă-mă");

        // Adăugare action listener pentru buton
        buton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("Butonul a fost apăsat!");
            }
        });

        // Creare panel și adăugare componente
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(textField);
        panel.add(buton);

        // Adăugare panel la fereastră
        add(panel);

        // Afișare fereastră
        setVisible(true);
    }

    public static void main(String[] args) {
        // Creare instanță a ferestrei
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ExempluSwing();
            }
        });
    }
}
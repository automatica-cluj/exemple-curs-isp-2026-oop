package utcluj.aut;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FileTextApp extends JFrame {
    private JTextField inputTextField;
    private JTextField fileNameTextField;
    private JTextArea displayTextArea;
    private JButton addButton;

    public FileTextApp() {
        // Configurare fereastră
        setTitle("Aplicație pentru adăugare text în fișier");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inițializare componente
        JLabel textLabel = new JLabel("Text:");
        inputTextField = new JTextField(20);

        JLabel fileLabel = new JLabel("Nume fișier:");
        fileNameTextField = new JTextField("output.txt", 15);

        addButton = new JButton("Adaugă");

        displayTextArea = new JTextArea(15, 40);
        displayTextArea.setEditable(false); // Făcut doar pentru citire
        JScrollPane scrollPane = new JScrollPane(displayTextArea); // Adăugare scroll

        // Organizarea componentelor folosind layout managers standard
        // Panel pentru textField și label
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        textPanel.add(textLabel);
        textPanel.add(inputTextField);

        // Panel pentru fileName și label
        JPanel filePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filePanel.add(fileLabel);
        filePanel.add(fileNameTextField);

        // Panel pentru buton, centrat
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);

        // Panel principal pentru input-uri folosind BoxLayout vertical
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(textPanel);
        topPanel.add(filePanel);
        topPanel.add(buttonPanel);

        // Organizare generală
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Adăugare acțiune buton
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTextToFile();
            }
        });

        // Adăugare listener pentru tastă Enter în inputTextField
        inputTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addTextToFile();
                }
            }
        });
    }

    private void addTextToFile() {
        String text = inputTextField.getText().trim();
        String fileName = fileNameTextField.getText().trim();

        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Te rog introdu un text!",
                    "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (fileName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Te rog specifică un nume pentru fișier!",
                    "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Adăugare text în fișier (append mode)
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(text);
            bufferedWriter.newLine();
            bufferedWriter.close();

            // Adăugare text în TextArea
            displayTextArea.append(text + "\n");

            // Golire TextField pentru introducerea unui text nou
            inputTextField.setText("");
            inputTextField.requestFocus();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Eroare la scrierea în fișier: " + ex.getMessage(),
                    "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FileTextApp().setVisible(true);
            }
        });
    }
}
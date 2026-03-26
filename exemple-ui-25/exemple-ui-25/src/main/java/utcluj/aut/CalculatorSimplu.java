package utcluj.aut;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorSimplu extends JFrame {

    // Declararea componentelor principale
    private JTextField display;
    private JPanel panouButoane;
    private double valoareCurenta = 0;
    private String operatieSelectata = "";
    private boolean inceputOperatie = true;

    public CalculatorSimplu() {
        // 1. Configurarea ferestrei principale
        setTitle("Calculator Industrial");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrează fereastra

        // 2. Crearea și configurarea layout-ului principal
        JPanel panou = new JPanel();
        panou.setLayout(new BorderLayout(10, 10));
        panou.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 3. Crearea componentelor UI
        // Display pentru rezultate
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);

        // Panou pentru organizarea butoanelor
        panouButoane = new JPanel();
        panouButoane.setLayout(new GridLayout(5, 4, 5, 5));

        // 4. Adăugarea componentelor în containere
        panou.add(display, BorderLayout.NORTH);
        panou.add(panouButoane, BorderLayout.CENTER);

        // Adăugarea panoului principal în fereastră
        add(panou);

        // 5. Crearea și adăugarea butoanelor
        creazaButoane();
    }

    private void creazaButoane() {
        // Butoane pentru calculator
        String[] eticheteButoane = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        // Adăugarea butoanelor în panou
        for (String eticheta : eticheteButoane) {
            JButton buton = new JButton(eticheta);
            buton.setFont(new Font("Arial", Font.BOLD, 18));

            // 6. Atașarea event handler-elor
            buton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    proceseazaButon(eticheta);
                }
            });

            panouButoane.add(buton);
        }

        // Adăugăm un buton de ștergere
        JButton butonStergere = new JButton("C");
        butonStergere.setFont(new Font("Arial", Font.BOLD, 18));
        butonStergere.setBackground(new Color(255, 150, 150));

        butonStergere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.setText("0");
                valoareCurenta = 0;
                operatieSelectata = "";
                inceputOperatie = true;
            }
        });

        panouButoane.add(butonStergere);
    }

    // 7. Implementarea logicii pentru procesarea acțiunilor butoanelor
    private void proceseazaButon(String comanda) {
        // Pentru butoanele numerice (0-9) și punct decimal
        if ((comanda.charAt(0) >= '0' && comanda.charAt(0) <= '9') || comanda.equals(".")) {
            if (inceputOperatie) {
                display.setText(comanda.equals(".") ? "0." : comanda);
                inceputOperatie = false;
            } else {
                // Prevenim adăugarea multiplă a punctului decimal
                if (comanda.equals(".") && display.getText().contains(".")) {
                    return;
                }
                display.setText(display.getText() + comanda);
            }
        }
        // Pentru butoanele de operații (+, -, *, /)
        else if (comanda.equals("+") || comanda.equals("-") || comanda.equals("*") || comanda.equals("/")) {
            try {
                valoareCurenta = Double.parseDouble(display.getText());
                operatieSelectata = comanda;
                inceputOperatie = true;
            } catch (NumberFormatException e) {
                display.setText("Eroare");
            }
        }
        // Pentru butonul de egal (=)
        else if (comanda.equals("=")) {
            try {
                double valoareSecunda = Double.parseDouble(display.getText());
                double rezultat = 0;

                // Efectuăm operația selectată
                switch (operatieSelectata) {
                    case "+":
                        rezultat = valoareCurenta + valoareSecunda;
                        break;
                    case "-":
                        rezultat = valoareCurenta - valoareSecunda;
                        break;
                    case "*":
                        rezultat = valoareCurenta * valoareSecunda;
                        break;
                    case "/":
                        if (valoareSecunda == 0) {
                            display.setText("Eroare: Div/0");
                            return;
                        }
                        rezultat = valoareCurenta / valoareSecunda;
                        break;
                    default:
                        rezultat = valoareSecunda;
                }

                // Afișăm rezultatul
                display.setText(String.valueOf(rezultat));
                valoareCurenta = rezultat;
                inceputOperatie = true;

            } catch (NumberFormatException e) {
                display.setText("Eroare");
            }
        }
    }

    // 8. Metoda main pentru lansarea aplicației
    public static void main(String[] args) {
        // Asigurăm rularea pe Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CalculatorSimplu calculator = new CalculatorSimplu();
                calculator.setVisible(true);
            }
        });
    }

}
// Pasul 7: Afișarea interfeței


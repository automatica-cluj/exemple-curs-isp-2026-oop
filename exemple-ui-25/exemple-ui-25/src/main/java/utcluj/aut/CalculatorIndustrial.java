package utcluj.aut;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class CalculatorIndustrial extends JFrame {

    // Componentele calculatorului
    private JTextField display;
    private JLabel unitaLabel;
    private JComboBox<String> unitatiMasura;
    private double valoareCurenta = 0;
    private String operatieSelectata = "";
    private boolean inceputOperatie = true;
    private DecimalFormat df = new DecimalFormat("###,###.##");

    public CalculatorIndustrial() {
        // Pasul 2: Configurarea ferestrei
        setTitle("Calculator Industrial");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Pasul 3: Configurarea layout manager-ului
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel displayPanel = new JPanel(new BorderLayout(5, 5));
        JPanel unitatiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel butoanePrincipalePanel = new JPanel(new GridLayout(5, 4, 10, 10));
        JPanel butoaneFunctiiPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        // Pasul 4: Crearea componentelor
        // Display pentru rezultate
        display = new JTextField("0");
        display.setFont(new Font("Digital-7", Font.BOLD, 36));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(new Color(220, 237, 200));
        display.setBorder(BorderFactory.createLoweredBevelBorder());

        // Unități de măsură
        unitaLabel = new JLabel("Unitate de măsură:");
        String[] unitati = {"Standard", "Kilograme", "Tone", "Metri", "Litri", "Amperi", "Volți"};
        unitatiMasura = new JComboBox<>(unitati);
        unitatiMasura.setBackground(new Color(220, 237, 223));

        // Butoane numerice și operații
        JButton[] butoneCifre = new JButton[10];
        for (int i = 0; i < 10; i++) {
            butoneCifre[i] = createButton(String.valueOf(i), new Color(240, 240, 240));
        }

        JButton butonPunct = createButton(".", new Color(240, 240, 240));
        JButton butonEgal = createButton("=", new Color(120, 180, 140));

        JButton butonAdunare = createButton("+", new Color(200, 220, 240));
        JButton butonScadere = createButton("-", new Color(200, 220, 240));
        JButton butonInmultire = createButton("×", new Color(200, 220, 240));
        JButton butonImpartire = createButton("÷", new Color(200, 220, 240));

        JButton butonPercentaj = createButton("%", new Color(200, 220, 240));
        JButton butonInversare = createButton("±", new Color(200, 220, 240));
        JButton butonStergere = createButton("C", new Color(255, 160, 160));

        // Butoane pentru funcții industriale
        JButton butonConversie = createButton("Conversie", new Color(180, 200, 220));
        JButton butonDebit = createButton("Debit", new Color(180, 200, 220));
        JButton butonPutere = createButton("Putere", new Color(180, 200, 220));

        // Pasul 5: Adăugarea componentelor în containere
        unitatiPanel.add(unitaLabel);
        unitatiPanel.add(unitatiMasura);

        displayPanel.add(display, BorderLayout.CENTER);
        displayPanel.add(unitatiPanel, BorderLayout.SOUTH);

        // Adăugarea butoanelor în grid
        butoanePrincipalePanel.add(butonStergere);
        butoanePrincipalePanel.add(butonInversare);
        butoanePrincipalePanel.add(butonPercentaj);
        butoanePrincipalePanel.add(butonImpartire);

        butoanePrincipalePanel.add(butoneCifre[7]);
        butoanePrincipalePanel.add(butoneCifre[8]);
        butoanePrincipalePanel.add(butoneCifre[9]);
        butoanePrincipalePanel.add(butonInmultire);

        butoanePrincipalePanel.add(butoneCifre[4]);
        butoanePrincipalePanel.add(butoneCifre[5]);
        butoanePrincipalePanel.add(butoneCifre[6]);
        butoanePrincipalePanel.add(butonScadere);

        butoanePrincipalePanel.add(butoneCifre[1]);
        butoanePrincipalePanel.add(butoneCifre[2]);
        butoanePrincipalePanel.add(butoneCifre[3]);
        butoanePrincipalePanel.add(butonAdunare);

        butoanePrincipalePanel.add(butoneCifre[0]);
        butoanePrincipalePanel.add(butonPunct);
        butoanePrincipalePanel.add(butonEgal);

        butoaneFunctiiPanel.add(butonConversie);
        butoaneFunctiiPanel.add(butonDebit);
        butoaneFunctiiPanel.add(butonPutere);

        mainPanel.add(displayPanel, BorderLayout.NORTH);
        mainPanel.add(butoanePrincipalePanel, BorderLayout.CENTER);
        mainPanel.add(butoaneFunctiiPanel, BorderLayout.SOUTH);

        // Adăugarea panel-ului principal în fereastră
        add(mainPanel);

        // Pasul 6: Atașarea event handler-elor
        // Pentru butoanele numerice
        for (int i = 0; i < 10; i++) {
            final int valoare = i;
            butoneCifre[i].addActionListener(e -> adaugaCifra(valoare));
        }

        // Pentru butoanele de operații
        butonPunct.addActionListener(e -> adaugaPunct());
        butonEgal.addActionListener(e -> calculeazaRezultat());
        butonStergere.addActionListener(e -> stergeDisplay());
        butonInversare.addActionListener(e -> inverseazaSemn());
        butonPercentaj.addActionListener(e -> calculeazaProcentaj());

        butonAdunare.addActionListener(e -> seteazaOperatie("+"));
        butonScadere.addActionListener(e -> seteazaOperatie("-"));
        butonInmultire.addActionListener(e -> seteazaOperatie("*"));
        butonImpartire.addActionListener(e -> seteazaOperatie("/"));

        // Pentru butoanele de funcții industriale
        butonConversie.addActionListener(e -> afiseazaDialogConversie());
        butonDebit.addActionListener(e -> calculeazaDebit());
        butonPutere.addActionListener(e -> calculeazaPutere());

        // Pentru combo box-ul cu unități
        unitatiMasura.addActionListener(e -> actualizeazaUnitati());

        // Pasul 8: Aspecte avansate
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Crearea meniului
        JMenuBar menuBar = new JMenuBar();
        JMenu menuCalculator = new JMenu("Calculator");
        menuCalculator.setMnemonic(KeyEvent.VK_C);

        JMenuItem itemStandard = new JMenuItem("Standard");
        JMenuItem itemAvansat = new JMenuItem("Avansat");
        JMenuItem itemIesire = new JMenuItem("Ieșire");

        itemStandard.addActionListener(e -> setareModStandard());
        itemAvansat.addActionListener(e -> setareModAvansat());
        itemIesire.addActionListener(e -> System.exit(0));

        menuCalculator.add(itemStandard);
        menuCalculator.add(itemAvansat);
        menuCalculator.addSeparator();
        menuCalculator.add(itemIesire);

        JMenu menuAjutor = new JMenu("Ajutor");
        JMenuItem itemDespre = new JMenuItem("Despre");
        itemDespre.addActionListener(e -> afiseazaDespre());
        menuAjutor.add(itemDespre);

        menuBar.add(menuCalculator);
        menuBar.add(menuAjutor);
        setJMenuBar(menuBar);
    }

    // Metode utilitare pentru crearea butoanelor
    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        return button;
    }

    // Implementarea metodelor pentru funcționalitatea calculatorului
    private void adaugaCifra(int cifra) {
        if (inceputOperatie) {
            display.setText("");
            inceputOperatie = false;
        }

        String valoareCurenta = display.getText();
        if (valoareCurenta.equals("0")) {
            display.setText(String.valueOf(cifra));
        } else {
            display.setText(valoareCurenta + cifra);
        }
    }

    private void adaugaPunct() {
        if (inceputOperatie) {
            display.setText("0.");
            inceputOperatie = false;
        } else {
            String valoareCurenta = display.getText();
            if (!valoareCurenta.contains(".")) {
                display.setText(valoareCurenta + ".");
            }
        }
    }

    private void seteazaOperatie(String operatie) {
        try {
            valoareCurenta = Double.parseDouble(display.getText());
            operatieSelectata = operatie;
            inceputOperatie = true;
        } catch (NumberFormatException e) {
            display.setText("Eroare");
        }
    }

    private void calculeazaRezultat() {
        try {
            double valoareSecunda = Double.parseDouble(display.getText());
            double rezultat = 0;

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
                        display.setText("Eroare: Împărțire la zero");
                        return;
                    }
                    rezultat = valoareCurenta / valoareSecunda;
                    break;
                default:
                    rezultat = valoareSecunda;
            }

            display.setText(df.format(rezultat));
            valoareCurenta = rezultat;
            inceputOperatie = true;

        } catch (NumberFormatException e) {
            display.setText("Eroare");
        }
    }

    private void stergeDisplay() {
        display.setText("0");
        valoareCurenta = 0;
        operatieSelectata = "";
        inceputOperatie = true;
    }

    private void inverseazaSemn() {
        try {
            double valoare = Double.parseDouble(display.getText());
            display.setText(df.format(-valoare));
        } catch (NumberFormatException e) {
            display.setText("Eroare");
        }
    }

    private void calculeazaProcentaj() {
        try {
            double valoare = Double.parseDouble(display.getText());
            double rezultat = valoare / 100;
            display.setText(df.format(rezultat));
        } catch (NumberFormatException e) {
            display.setText("Eroare");
        }
    }

    // Metode pentru funcțiile industriale
    private void afiseazaDialogConversie() {
        String unitateCurenta = (String) unitatiMasura.getSelectedItem();
        JDialog dialogConversie = new JDialog(this, "Conversie " + unitateCurenta, true);
        dialogConversie.setSize(300, 200);
        dialogConversie.setLocationRelativeTo(this);

        JPanel panelConversie = new JPanel(new GridLayout(4, 1, 10, 10));
        panelConversie.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // În funcție de unitatea selectată, afișează opțiunile relevante
        JLabel labelInfo = new JLabel("Selectați unitatea pentru conversie:");
        JComboBox<String> unitatiConversie = new JComboBox<>();

        switch (unitateCurenta) {
            case "Kilograme":
                unitatiConversie.addItem("Grame");
                unitatiConversie.addItem("Tone");
                unitatiConversie.addItem("Pounds");
                break;
            case "Metri":
                unitatiConversie.addItem("Centimetri");
                unitatiConversie.addItem("Kilometri");
                unitatiConversie.addItem("Feet");
                break;
            default:
                unitatiConversie.addItem("Unitate standard");
        }

        JButton butonConvert = new JButton("Convertește");
        JLabel rezultatConversie = new JLabel("Rezultat: ");

        butonConvert.addActionListener(e -> {
            try {
                double valoare = Double.parseDouble(display.getText());
                String unitateDestinatie = (String) unitatiConversie.getSelectedItem();
                double rezultat = convertesteUnitate(valoare, unitateCurenta, unitateDestinatie);
                rezultatConversie.setText("Rezultat: " + df.format(rezultat) + " " + unitateDestinatie);
            } catch (Exception ex) {
                rezultatConversie.setText("Eroare la conversie");
            }
        });

        panelConversie.add(labelInfo);
        panelConversie.add(unitatiConversie);
        panelConversie.add(butonConvert);
        panelConversie.add(rezultatConversie);

        dialogConversie.add(panelConversie);
        dialogConversie.setVisible(true);
    }

    private double convertesteUnitate(double valoare, String unitateSursa, String unitateDestinatie) {
        // Implementare simplificată pentru conversii
        if (unitateSursa.equals("Kilograme")) {
            if (unitateDestinatie.equals("Grame")) return valoare * 1000;
            if (unitateDestinatie.equals("Tone")) return valoare / 1000;
            if (unitateDestinatie.equals("Pounds")) return valoare * 2.20462;
        } else if (unitateSursa.equals("Metri")) {
            if (unitateDestinatie.equals("Centimetri")) return valoare * 100;
            if (unitateDestinatie.equals("Kilometri")) return valoare / 1000;
            if (unitateDestinatie.equals("Feet")) return valoare * 3.28084;
        }
        return valoare; // În caz că nu se găsește o conversie
    }

    private void calculeazaDebit() {
        try {
            // Deschide un dialog pentru calculul debitului
            JDialog dialogDebit = new JDialog(this, "Calculator de Debit", true);
            dialogDebit.setSize(350, 250);
            dialogDebit.setLocationRelativeTo(this);

            JPanel panelDebit = new JPanel(new GridLayout(5, 2, 10, 10));
            panelDebit.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel labelDiametru = new JLabel("Diametru (mm):");
            JTextField fieldDiametru = new JTextField("100");

            JLabel labelViteza = new JLabel("Viteză (m/s):");
            JTextField fieldViteza = new JTextField("1.5");

            JLabel labelDensitate = new JLabel("Densitate (kg/m³):");
            JTextField fieldDensitate = new JTextField("1000");

            JButton butonCalculeaza = new JButton("Calculează");
            JLabel rezultatDebit = new JLabel("Debit: ");

            butonCalculeaza.addActionListener(e -> {
                try {
                    double diametru = Double.parseDouble(fieldDiametru.getText()) / 1000; // mm la m
                    double viteza = Double.parseDouble(fieldViteza.getText());
                    double densitate = Double.parseDouble(fieldDensitate.getText());

                    // Formula: Q = A * v
                    double area = Math.PI * Math.pow(diametru/2, 2);
                    double debitVolumMetriCubi = area * viteza;
                    double debitMasa = debitVolumMetriCubi * densitate;

                    rezultatDebit.setText("<html>Debit volumic: " + df.format(debitVolumMetriCubi * 3600) + " m³/h<br>" +
                            "Debit masic: " + df.format(debitMasa * 3600) + " kg/h</html>");

                    // Actualizarea display-ului principal cu debitul volumic
                    display.setText(df.format(debitVolumMetriCubi * 3600));
                } catch (NumberFormatException ex) {
                    rezultatDebit.setText("Eroare: Introduceți valori numerice valide");
                }
            });

            panelDebit.add(labelDiametru);
            panelDebit.add(fieldDiametru);
            panelDebit.add(labelViteza);
            panelDebit.add(fieldViteza);
            panelDebit.add(labelDensitate);
            panelDebit.add(fieldDensitate);
            panelDebit.add(butonCalculeaza);
            panelDebit.add(rezultatDebit);

            dialogDebit.add(panelDebit);
            dialogDebit.setVisible(true);
        } catch (Exception e) {
            display.setText("Eroare");
        }
    }

    private void calculeazaPutere() {
        // Dialog pentru calculul puterii electrice
        JDialog dialogPutere = new JDialog(this, "Calculator de Putere Electrică", true);
        dialogPutere.setSize(350, 250);
        dialogPutere.setLocationRelativeTo(this);

        JPanel panelPutere = new JPanel(new GridLayout(5, 2, 10, 10));
        panelPutere.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel labelTensiune = new JLabel("Tensiune (V):");
        JTextField fieldTensiune = new JTextField("230");

        JLabel labelCurent = new JLabel("Curent (A):");
        JTextField fieldCurent = new JTextField("10");

        JLabel labelFactorPutere = new JLabel("Factor de putere:");
        JTextField fieldFactorPutere = new JTextField("0.9");

        JButton butonCalculeaza = new JButton("Calculează");
        JLabel rezultatPutere = new JLabel("Putere: ");

        butonCalculeaza.addActionListener(e -> {
            try {
                double tensiune = Double.parseDouble(fieldTensiune.getText());
                double curent = Double.parseDouble(fieldCurent.getText());
                double factorPutere = Double.parseDouble(fieldFactorPutere.getText());

                // Calcul putere aparentă, activă și reactivă
                double putereAparenta = tensiune * curent;
                double putereActiva = putereAparenta * factorPutere;
                double putereReactiva = Math.sqrt(Math.pow(putereAparenta, 2) - Math.pow(putereActiva, 2));

                rezultatPutere.setText("<html>Putere aparentă: " + df.format(putereAparenta) + " VA<br>" +
                        "Putere activă: " + df.format(putereActiva) + " W<br>" +
                        "Putere reactivă: " + df.format(putereReactiva) + " VAR</html>");

                // Actualizarea display-ului principal cu puterea activă
                display.setText(df.format(putereActiva));
            } catch (NumberFormatException ex) {
                rezultatPutere.setText("Eroare: Introduceți valori numerice valide");
            }
        });

        panelPutere.add(labelTensiune);
        panelPutere.add(fieldTensiune);
        panelPutere.add(labelCurent);
        panelPutere.add(fieldCurent);
        panelPutere.add(labelFactorPutere);
        panelPutere.add(fieldFactorPutere);
        panelPutere.add(butonCalculeaza);
        panelPutere.add(rezultatPutere);

        dialogPutere.add(panelPutere);
        dialogPutere.setVisible(true);
    }

    private void actualizeazaUnitati() {
        String unitateSelectata = (String) unitatiMasura.getSelectedItem();
        setTitle("Calculator Industrial - " + unitateSelectata);
    }

    private void setareModStandard() {
        // Implementare pentru modul standard al calculatorului
        JOptionPane.showMessageDialog(this,
                "Ați activat modul standard al calculatorului",
                "Mod Standard",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void setareModAvansat() {
        // Implementare pentru modul avansat al calculatorului
        JOptionPane.showMessageDialog(this,
                "Ați activat modul avansat al calculatorului",
                "Mod Avansat",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void afiseazaDespre() {
        JOptionPane.showMessageDialog(this,
                "Calculator Industrial v1.0\n" +
                        "Dezvoltat pentru demonstrarea interfețelor grafice în Java Swing\n" +
                        "© 2025 Departamentul de Inginerie Industrială",
                "Despre Calculator Industrial",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Pasul 7: Afișarea interfeței
    public static void main(String[] args) {
        // Crearea și afișarea ferestrei pe Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CalculatorIndustrial calculator = new CalculatorIndustrial();
                calculator.setVisible(true);
            }
        });
    }
}
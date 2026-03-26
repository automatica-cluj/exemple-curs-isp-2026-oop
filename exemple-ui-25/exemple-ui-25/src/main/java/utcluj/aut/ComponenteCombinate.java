package utcluj.aut;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComponenteCombinate {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Formular de comandă");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Panou pentru produse (JList)
        DefaultListModel<String> modelProduse = new DefaultListModel<>();
        modelProduse.addElement("Laptop");
        modelProduse.addElement("Smartphone");
        modelProduse.addElement("Tabletă");
        modelProduse.addElement("Monitor");
        modelProduse.addElement("Imprimantă");

        JList<String> listaProduse = new JList<>(modelProduse);
        listaProduse.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaProduse);

        // Panou pentru metoda de plată (JRadioButton)
        JPanel panouPlata = new JPanel();
        panouPlata.setBorder(BorderFactory.createTitledBorder("Metoda de plată:"));
        panouPlata.setLayout(new BoxLayout(panouPlata, BoxLayout.Y_AXIS));

        JRadioButton radioCard = new JRadioButton("Card");
        JRadioButton radioCash = new JRadioButton("Numerar");
        JRadioButton radioTransfer = new JRadioButton("Transfer bancar");

        ButtonGroup grupPlata = new ButtonGroup();
        grupPlata.add(radioCard);
        grupPlata.add(radioCash);
        grupPlata.add(radioTransfer);
        radioCard.setSelected(true);

        panouPlata.add(radioCard);
        panouPlata.add(radioCash);
        panouPlata.add(radioTransfer);

        // Panou pentru livrare (JComboBox)
        JPanel panouLivrare = new JPanel();
        panouLivrare.setBorder(BorderFactory.createTitledBorder("Metoda de livrare:"));

        String[] metode = {"Curier standard", "Curier rapid", "Ridicare personală"};
        JComboBox<String> comboLivrare = new JComboBox<>(metode);
        panouLivrare.add(comboLivrare);

        // Panou pentru opțiuni și rezultat
        JPanel panouOptiuni = new JPanel(new GridLayout(2, 1));
        panouOptiuni.add(panouPlata);
        panouOptiuni.add(panouLivrare);

        JTextArea rezultat = new JTextArea(8, 30);
        rezultat.setEditable(false);

        // Buton pentru finalizare
        JButton butonFinalizare = new JButton("Finalizează comanda");
        butonFinalizare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                sb.append("COMANDĂ FINALIZATĂ\n\n");

                sb.append("Produse selectate:\n");
                for (String produs : listaProduse.getSelectedValuesList()) {
                    sb.append("- ").append(produs).append("\n");
                }

                sb.append("\nMetoda de plată: ");
                if (radioCard.isSelected()) sb.append("Card");
                else if (radioCash.isSelected()) sb.append("Numerar");
                else sb.append("Transfer bancar");

                sb.append("\nMetoda de livrare: ").append(comboLivrare.getSelectedItem());

                rezultat.setText(sb.toString());
            }
        });

        // Adăugarea componentelor în frame
        frame.add(new JLabel("Selectați produsele:"), BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.WEST);
        frame.add(panouOptiuni, BorderLayout.EAST);
        frame.add(new JScrollPane(rezultat), BorderLayout.CENTER);
        frame.add(butonFinalizare, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
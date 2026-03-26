package utcluj.aut;

import javax.swing.*;
import javax.swing.table.*;
import java.util.ArrayList;

public class ExempluTabelArrayListPersonalizat {
    public static void main(String[] args) {
        // Crearea și popularea ArrayList-ului
        ArrayList<Persoana> listaPersone = new ArrayList<>();
        listaPersone.add(new Persoana("Ion Popescu", 25, "Inginer"));
        listaPersone.add(new Persoana("Maria Ionescu", 30, "Profesor"));
        listaPersone.add(new Persoana("Andrei Vasilescu", 28, "Medic"));
        listaPersone.add(new Persoana("Elena Dumitrescu", 35, "Avocat"));

        // Crearea unui model de tabel personalizat
        PersoanaTableModel model = new PersoanaTableModel(listaPersone);

        // Crearea tabelului cu modelul de date
        JTable tabel = new JTable(model);

        // Adăugarea tabelului într-un JScrollPane pentru a permite derularea
        JScrollPane scrollPane = new JScrollPane(tabel);

        // Crearea și configurarea ferestrei
        JFrame fereastra = new JFrame("Afișare ArrayList în JTable");
        fereastra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fereastra.add(scrollPane);
        fereastra.setSize(500, 300);
        fereastra.setVisible(true);
    }

    // Clasa pentru modelul de tabel personalizat
    static class PersoanaTableModel extends AbstractTableModel {
        private final String[] numeColoane = {"Nume", "Vârstă", "Profesie"};
        private ArrayList<Persoana> listaPersone;

        public PersoanaTableModel(ArrayList<Persoana> listaPersone) {
            this.listaPersone = listaPersone;
        }

        @Override
        public int getRowCount() {
            return listaPersone.size();
        }

        @Override
        public int getColumnCount() {
            return numeColoane.length;
        }

        @Override
        public Object getValueAt(int row, int col) {
            Persoana persoana = listaPersone.get(row);

            switch (col) {
                case 0: return persoana.getNume();
                case 1: return persoana.getVarsta();
                case 2: return persoana.getProfesie();
                default: return null;
            }
        }

        @Override
        public String getColumnName(int col) {
            return numeColoane[col];
        }

        @Override
        public Class<?> getColumnClass(int col) {
            switch (col) {
                case 0: return String.class;
                case 1: return Integer.class;
                case 2: return String.class;
                default: return Object.class;
            }
        }
    }
}

class Persoana {
    private String nume;
    private int varsta;
    private String profesie;

    public Persoana(String nume, int varsta, String profesie) {
        this.nume = nume;
        this.varsta = varsta;
        this.profesie = profesie;
    }

    public String getNume() {
        return nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public String getProfesie() {
        return profesie;
    }
}

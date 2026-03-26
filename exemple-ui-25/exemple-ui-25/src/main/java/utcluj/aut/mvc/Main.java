package utcluj.aut.mvc;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Model (CalculatorModel.java)
class CalculatorModel {
    private int result;
    private List<ModelObserver> observers = new ArrayList<>();

    public interface ModelObserver {
        void modelUpdated();
    }

    public void addObserver(ModelObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (ModelObserver observer : observers) {
            observer.modelUpdated();
        }
    }

    public void add(int num1, int num2) {
        result = num1 + num2;
        notifyObservers();
    }

    public int getResult() {
        return result;
    }
}

// View (CalculatorView.java)
class CalculatorView extends JFrame implements CalculatorModel.ModelObserver {
    private JTextField num1Field = new JTextField(10);
    private JTextField num2Field = new JTextField(10);
    private JButton addButton = new JButton("Adună");
    private JLabel resultLabel = new JLabel("Rezultat: 0");

    private CalculatorModel model;

    public CalculatorView(CalculatorModel model) {
        this.model = model;
        model.addObserver(this);

        setTitle("Calculator MVC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new FlowLayout());

        add(new JLabel("Primul număr:"));
        add(num1Field);
        add(new JLabel("Al doilea număr:"));
        add(num2Field);
        add(addButton);
        add(resultLabel);

        setVisible(true);
    }

    public void addCalculateListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public int getFirstNumber() {
        try {
            return Integer.parseInt(num1Field.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getSecondNumber() {
        try {
            return Integer.parseInt(num2Field.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public void modelUpdated() {
        resultLabel.setText("Rezultat: " + model.getResult());
    }
}

// Controller (CalculatorController.java)
class CalculatorController {
    private CalculatorModel model;
    private CalculatorView view;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;

        this.view.addCalculateListener(new CalculateListener());
    }

    class CalculateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int num1 = view.getFirstNumber();
            int num2 = view.getSecondNumber();
            model.add(num1, num2);
        }
    }
}

// Main (Main.java)
public class Main {
    public static void main(String[] args) {
        // Creăm componentele MVC
        CalculatorModel model = new CalculatorModel();
        CalculatorView view = new CalculatorView(model);
        CalculatorController controller = new CalculatorController(model, view);
    }
}
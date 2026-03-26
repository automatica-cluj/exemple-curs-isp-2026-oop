package utcluj.isp.curs3.liste.demoLab7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DoorLockController {
    private final String correctCode;
    private boolean status;

    public DoorLockController(String correctCode) {
        this.correctCode = correctCode;

    }


    public String enterPin(String pin) throws Exception {
        System.out.println("Entered pin: " + pin);

        if(!pin.equals(correctCode))
            throw new Exception("Invalid pin!");

        System.out.println("Door is opening or closing...");
        status = !status;
        return status ? "OPEN" : "CLOSE";

    }

}

public class NumpadUI extends JFrame implements ActionListener {
    private JTextField displayField; // To display the pressed digits
    private JButton[] numberButtons; // Buttons for digits 0-9
    private JButton okButton; // The "OK" button
    private DoorLockController doorLockController = new DoorLockController("1234");
    private boolean codeEntered;

    public NumpadUI() {
        setTitle("Numpad UI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize the display field
        displayField = new JTextField();
        displayField.setEditable(false);
        add(displayField, BorderLayout.NORTH);

        // Initialize number buttons and the OK button
        JPanel buttonPanel = new JPanel(new GridLayout(4, 3)); // Grid layout for buttons
        numberButtons = new JButton[10]; // 10 digits
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            buttonPanel.add(numberButtons[i]);
        }

        // Adding OK button
        okButton = new JButton("OK");
        okButton.addActionListener(this);
        buttonPanel.add(okButton);

        // Add button panel to the frame
        add(buttonPanel, BorderLayout.CENTER);
        pack(); // Size the frame to fit the preferred sizes of its components
        setVisible(true);
    }

    // Action performed method to handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        // For number buttons, append the digit to the text field
        for (JButton button : numberButtons) {
            if(codeEntered){
                displayField.setText("");
                codeEntered = false;
            }

            if (e.getSource() == button) {
                displayField.setText(displayField.getText() + button.getText());
                return;
            }
        }

        // For the OK button, perform desired action (e.g., print digits to console)
        if (e.getSource() == okButton) {
            codeEntered = true;
            System.out.println("Entered digits: " + displayField.getText());
            try {
                String result = doorLockController.enterPin(displayField.getText());
                displayField.setText(result);
            } catch (Exception ex) {
                displayField.setText(ex.getMessage());
            }
            // Optionally, clear the display field or perform other actions
            //displayField.setText(""); // Clearing the display field
        }
    }

    public static void main(String[] args) {
        // Ensure GUI creation is done on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumpadUI();
            }
        });
    }
}

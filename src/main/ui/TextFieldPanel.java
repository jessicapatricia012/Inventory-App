package ui;

import javax.swing.*;

public class TextFieldPanel extends JPanel {
    private JTextField textField;
    private JLabel itemNameLabel;
    private JLabel itemQuantityLabel;
    private JButton okButton;


    public TextFieldPanel() {
        textField = new JTextField();
        itemNameLabel = new JLabel("Item Name:");
        itemQuantityLabel = new JLabel("Item Quantity");
        okButton = new JButton("Ok");

        setSize(200, 100);
        setVisible(true);
        setLayout(null);

        addComponents();
    }

    private void addComponents() {
        add(textField);
        add(itemNameLabel);
        add(itemQuantityLabel);
        add(okButton);
    }

}

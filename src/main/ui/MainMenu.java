package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel implements ActionListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JLabel mainMenuLabel;
    private JButton receiveItemsButton;
    private JButton shipItemsOutButton;
    private JButton lowStockWarningsButton;
    private JLayeredPane lp;

    public MainMenu(JLayeredPane lp) {
        this.lp = lp;
        mainMenuLabel = new JLabel("Main Menu");
        receiveItemsButton = new JButton("Receive Items");
        shipItemsOutButton = new JButton("Ship Items Out");
        lowStockWarningsButton = new JButton("Low Stock Warnings");
        setUp();
    }

    public void setUp() {
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLayout(null);

        setUpButtons();
        add(receiveItemsButton);
        add(shipItemsOutButton);
        add(lowStockWarningsButton);

        setUpLabel();
        add(mainMenuLabel);

    }

    public void setUpButtons() {
        receiveItemsButton.setBounds(200, 175, 400, 50);
        shipItemsOutButton.setBounds(200, 250, 400, 50);
        lowStockWarningsButton.setBounds(200, 400, 400, 50);

        receiveItemsButton.setFocusable(false);
        shipItemsOutButton.setFocusable(false);
        lowStockWarningsButton.setFocusable(false);

        receiveItemsButton.addActionListener(this);
        shipItemsOutButton.addActionListener(this);
        lowStockWarningsButton.addActionListener(this);
    }

    public void setUpLabel() {
        mainMenuLabel.setHorizontalAlignment(JLabel.CENTER);
        mainMenuLabel.setVerticalAlignment(JLabel.TOP);
        mainMenuLabel.setBounds(0, 10, 800, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == receiveItemsButton) {

        } else if (e.getSource() == shipItemsOutButton) {

        } else if (e.getSource() == lowStockWarningsButton) {

        }
    }

}

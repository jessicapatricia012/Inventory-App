//package ui;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class RegisterNewShipmentMenu extends JPanel implements ActionListener {
//    private static final int WIDTH = 800;
//    private static final int HEIGHT = 600;
//
//    //private JLabel selectOption;
//    private JButton receiveItemsButton;
//    private JButton shipOutItemsButton;
//
//    public RegisterNewShipmentMenu() {
//        //selectOption = new JLabel("Select Option");
//        receiveItemsButton = new JButton("Receive Items");
//        shipOutItemsButton = new JButton("Ship Out Items");
//        setUp();
//    }
//
//    public void setUp() {
//        setSize(WIDTH, HEIGHT);
//        setVisible(true);
//        setLayout(null);
//        setBackground(Color.GRAY);
//
//        setUpButtons();
//        add(receiveItemsButton);
//        add(shipOutItemsButton);
//
//        //setUpLabel();
//        //add(selectOption);
//
//    }
//
//    public void setUpButtons() {
//        receiveItemsButton.setBounds(200, 175, 400, 50);
//        shipOutItemsButton.setBounds(200, 250, 400, 50);
//        receiveItemsButton.setFocusable(false);
//        shipOutItemsButton.setFocusable(false);
//        receiveItemsButton.addActionListener(this);
//        shipOutItemsButton.addActionListener(this);
//
//    }
//
////    public void setUpLabel() {
////        selectOption.setHorizontalAlignment(JLabel.CENTER);
////        selectOption.setVerticalAlignment(JLabel.TOP);
////        selectOption.setBounds(0, 10, 800, 50);
////    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == receiveItemsButton) {
//
//        } else if (e.getSource() == shipOutItemsButton) {
//
//        }
//    }
//
//}

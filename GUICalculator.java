package com.mypackage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUICalculator {
    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10]; // more clean array to hold buttons pointer
    JButton addButton, subButton, mulButton, divButton, clrButton, decButton, eqlButton, mortgageButton;
    JPanel panel;
    Font font = new Font("Arial", Font.PLAIN, 20);
    double num1 = 0, num2 = 0, result = 0;
    char operator;

    public GUICalculator() {
        frame = new JFrame("GUICalculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 650);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(font);
        textField.setEditable(false);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        clrButton = new JButton("C");
        decButton = new JButton(".");
        eqlButton = new JButton("=");
        mortgageButton = new JButton("Calculate Mortgage");

        JButton[] buttons = {addButton, subButton, mulButton, divButton, clrButton, decButton, eqlButton, mortgageButton};

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(new ButtonListener());
            buttons[i].setFont(font);
            buttons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(new ButtonListener());
            numberButtons[i].setFont(font);
            numberButtons[i].setFocusable(false);
        }

        clrButton.setBounds(50, 430, 100, 50);
        decButton.setBounds(150, 430, 100, 50);
        eqlButton.setBounds(250, 430, 100, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(Color.gray);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(eqlButton);
        panel.add(divButton);

        mortgageButton.setBounds(50, 500, 300, 50);

        frame.add(panel);
        frame.add(clrButton);
        frame.add(textField);
        frame.add(mortgageButton);
        frame.setVisible(true);
    }

    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 10; i++) {
                if (e.getSource() == numberButtons[i]) {
                    textField.setText(textField.getText().concat(String.valueOf(i)));
                }
            }
            if (e.getSource() == decButton) {
                textField.setText(textField.getText().concat("."));
            }
            if (e.getSource() == addButton) {
                num1 = Double.parseDouble(textField.getText());
                operator = '+';
                textField.setText("");
            }
            if (e.getSource() == subButton) {
                num1 = Double.parseDouble(textField.getText());
                operator = '-';
                textField.setText("");
            }
            if (e.getSource() == mulButton) {
                num1 = Double.parseDouble(textField.getText());
                operator = '*';
                textField.setText("");
            }
            if (e.getSource() == divButton) {
                num1 = Double.parseDouble(textField.getText());
                operator = '/';
                textField.setText("");
            }
            if (e.getSource() == eqlButton) {
                num2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        result = num1 / num2;
                        break;
                }
                textField.setText(String.valueOf(result));
            }
            if (e.getSource() == clrButton) {
                textField.setText("");
            }
            // Calculate Mortgage Button Action
            if (e.getSource() == mortgageButton) {
                // Prompt user for interest rate and years
                String rateInput = JOptionPane.showInputDialog(frame, "Enter annual interest rate (in decimal form):");
                String yearsInput = JOptionPane.showInputDialog(frame, "Enter the number of years:");

                // Convert user input to double for rate and integer for years
                double rate = Double.parseDouble(rateInput);
                int years = Integer.parseInt(yearsInput);

                double principal = Double.parseDouble(textField.getText());
                double monthlyPayment = calculateMonthlyPayment(principal, years, rate);
                textField.setText(String.format("%.2f", monthlyPayment));
            }
        }
    }

    // Method to calculate monthly mortgage payment
    public double calculateMonthlyPayment(double principal, int years, double rate) {
        double monthlyRate = rate/100/12;
        int months = years * 12;
        double mathPower = Math.pow(1 + monthlyRate, months); //Takes the first arg to the power of the 2nd arg
        double monthlyPayment = principal * (monthlyRate * mathPower / (mathPower - 1));
        return monthlyPayment;
    }

    public static void main(String[] args) {
        new GUICalculator();
    }
}



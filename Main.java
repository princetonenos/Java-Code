import java.awt.*;
import java.awt.event.*;
import java.util.InputMismatchException;
import java.util.Random;
import javax.swing.*;

public class Main {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField textField;
    private JButton button;
    private int randomNumber;
    private int remainingGuesses;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main().createAndShowGUI();
            }
        });
    }

    private void createAndShowGUI() {
        frame = new JFrame("Number Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        
        label = new JLabel("Enter a number FROM 1 to 100:");
        panel.add(label, constraints);
        
        constraints.gridy = 1;
        textField = new JTextField(10);
        panel.add(textField, constraints);
        
        constraints.gridy = 2;
        button = new JButton("Guess");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });
        panel.add(button, constraints);
        
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        
        generateRandomNumber();
        remainingGuesses = 5;
    }

    private void generateRandomNumber() {
        Random number = new Random();
        randomNumber = number.nextInt(100) + 1;
    }
    
    private void handleGuess() {
        try {
            int inputNumber = Integer.parseInt(textField.getText());
            if (inputNumber >= 1 && inputNumber <= 100) {
                remainingGuesses--;
                if (randomNumber == inputNumber) {
                    showResultDialog("HURRAY!! YOU FOUND THE NUMBER", "Congratulations");
                    resetGame();
                } else if (remainingGuesses > 0) {
                    String message = "OOPS! NOT THAT ONE! Remaining Guesses: " + remainingGuesses;
                    showResultDialog(message, "Wrong Guess");
                } else {
                    String message = "Sorry, you ran out of guesses! The number was: " + randomNumber;
                    showResultDialog(message, "Game Over");
                    resetGame();
                }
            } else {
                showInvalidInputDialog("Enter a number FROM 1 to 100", "Invalid Input");
            }
        } catch (NumberFormatException ex) {
            showInvalidInputDialog("Enter a valid integer number", "Invalid Input");
        }
        
        textField.setText("");
    }
    
    private void resetGame() {
        remainingGuesses = 5;
        generateRandomNumber();
    }
    
    private void showResultDialog(String message, String title) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showInvalidInputDialog(String message, String title) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.ERROR_MESSAGE);
    }
}

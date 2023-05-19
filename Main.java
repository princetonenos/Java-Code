import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {
    private static Random random = new Random();

    public static void main(String[] args) {
        SwingGUI gui = new SwingGUI();
        gui.createAndShowGUI();
    }

    public static class SwingGUI {
        private JFrame frame;
        private JTextField inputField;
        private JLabel messageLabel;
        private int randomNumber;

        public void createAndShowGUI() {
            frame = new JFrame("Number Guessing Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());

            messageLabel = new JLabel("Enter a number FROM 1 to 10:");
            mainPanel.add(messageLabel, BorderLayout.NORTH);

            inputField = new JTextField(10);
            mainPanel.add(inputField, BorderLayout.CENTER);

            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(new SubmitButtonListener());
            mainPanel.add(submitButton, BorderLayout.SOUTH);

            frame.getContentPane().add(mainPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            startGame();
        }

        private void startGame() {
            randomNumber = random.nextInt(10) + 1;
            inputField.setText("");
            inputField.requestFocus();
        }

        private class SubmitButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int inputNumber = Integer.parseInt(inputField.getText());
                    if (inputNumber >= 1 && inputNumber <= 10) {
                        checkNumber(inputNumber);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Enter a number FROM 1 to 10!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Enter a valid integer number!");
                }
            }
        }

        private void checkNumber(int inputNumber) {
            if (inputNumber == randomNumber) {
                JOptionPane.showMessageDialog(frame, "HURRAY!! YOU FOUND THE NUMBER\n:)");
                int choice = JOptionPane.showConfirmDialog(frame, "Do you want to play again?", "Game Over",
                        JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    startGame();
                } else {
                    frame.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(frame, "OOPS! NOT THAT ONE! Try again.");
                inputField.setText("");
                inputField.requestFocus();
            }
        }
    }
}
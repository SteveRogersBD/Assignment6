import javax.swing.*;
import java.awt.*;

public class ExperimentGUI extends JFrame {
    private JRadioButton option1, option2, option3, option4;
    private JButton submitButton, restartButton, nextButton, backButton;
    private ButtonGroup group;

    public ExperimentGUI() {
        createUI();
    }

    private void createUI() {
        setTitle("Game Quiz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel questionLabel = new JLabel("Question 1 of 5: In which museum can you find Leonardo Da Vinci's Mona Lisa?");
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(questionLabel);

        option1 = new JRadioButton("Le Louvre");
        option2 = new JRadioButton("Uffizi Museum");
        option3 = new JRadioButton("British Museum");
        option4 = new JRadioButton("Metropolitan Museum of Art");
        group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);
        group.add(option4);

        panel.add(option1);
        panel.add(option2);
        panel.add(option3);
        panel.add(option4);

        JPanel buttonPanel = new JPanel();
        restartButton = new JButton("Restart");
        backButton = new JButton("Back");
        nextButton = new JButton("Next");
        submitButton = new JButton("Submit");
        buttonPanel.add(restartButton);
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(submitButton);

        panel.add(buttonPanel);
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ExperimentGUI().setVisible(true);
            }
        });
    }
}

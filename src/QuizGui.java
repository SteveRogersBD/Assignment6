import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents the GUI for a quiz game.
 * It allows users to answer multiple-choice questions and provides navigation and scoring functionalities.
 * It also dynamically generates questions from an external file.
 *
 * @author Aniruddha Biswas Atanu
 */
public class QuizGui implements ActionListener {

    public static JRadioButton rb1, rb2, rb3, rb4;
    public static JButton b1, b2, b3, b4;
    public static JLabel north;
    public static int ca = 0;
    public static int total = 0;
    public static int qIndex = 0;
    public static ArrayList<QuizQuestion> qList = new ArrayList<>();
    public static int[] userAnswer = new int[5];
    public static int[] correctAnswer = new int[5];


    /**
     * Constructor for QuizGui class. Sets up the GUI components.
     *
     * @throws FileNotFoundException if the file containing quiz questions is not found.
     */
    QuizGui() throws FileNotFoundException {
        // Generate questions
        generateQuestion();
        //filling the userAnswer with -1;
        for (int i = 0; i < 5; i++) userAnswer[i] = -1;


        // Create the main frame
        JFrame frame = new JFrame("Game Quiz");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(600, 450));
        frame.setVisible(true);
        frame.setBackground(Color.WHITE);

        // North panel with JLabel
        north = new JLabel("What is your name?");
        JPanel northP = new JPanel(new BorderLayout());
        northP.setPreferredSize(new Dimension(350, 100));
        northP.add(north, BorderLayout.CENTER);
        north.setHorizontalAlignment(JLabel.CENTER);
        frame.add(northP, BorderLayout.NORTH);
        //  Center panel with radio buttons
        JPanel center = new JPanel(); // Aligns radio buttons to center
        center.setPreferredSize(new Dimension(400, 100));
        center.setLayout(new BorderLayout());
        addRadioButtons(center);
        frame.add(center, BorderLayout.CENTER);

        // South panel with buttons
        JPanel south = new JPanel(new BorderLayout());
        addButtons(south);
        frame.add(south, BorderLayout.SOUTH);


        // Reset radio buttons
        reset();
    }

    /**
     * Adds radio buttons to the specified panel.
     *
     * @param panel the panel to which radio buttons are added.
     */
    private void addRadioButtons(JPanel panel) {
        rb1 = new JRadioButton("Option 1");
        rb1.addActionListener(this);
        rb2 = new JRadioButton("Option 2");
        rb2.addActionListener(this);
        rb3 = new JRadioButton("Option 3");
        rb3.addActionListener(this);
        rb4 = new JRadioButton("Option 4");
        rb4.addActionListener(this);
        ButtonGroup rButtons = new ButtonGroup();
        rButtons.add(rb1);
        rButtons.add(rb2);
        rButtons.add(rb3);
        rButtons.add(rb4);
        Dimension radioButtonSize = new Dimension(200, 50); // Adjust dimensions as needed
        rb1.setPreferredSize(radioButtonSize);
        rb2.setPreferredSize(radioButtonSize);
        rb3.setPreferredSize(radioButtonSize);
        rb4.setPreferredSize(radioButtonSize);
        JPanel panUp = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panDown = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panUp.add(rb1);
        panUp.add(rb2);
        panDown.add(rb3);
        panDown.add(rb4);
        panel.add(panUp, BorderLayout.NORTH);
        panel.add(panDown, BorderLayout.SOUTH);
    }

    /**
     * Adds buttons to the specified panel.
     *
     * @param panel the panel to which buttons are added.
     */
    private void addButtons(JPanel panel) {
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        b1 = new JButton("Restart");
        b1.setBackground(Color.WHITE);
        b1.addActionListener(e -> {
            try {
                generateQuestion();
                qIndex = 0;
                displayQuestion(qIndex);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            reset();
        });

        b2 = new JButton("Back");
        b2.addActionListener(e -> {
            if (qIndex > 0) qIndex--;

            try {
                displayQuestion(qIndex);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            reset();
        });
        b2.setBackground(Color.WHITE);

        b3 = new JButton("Next");
        b3.addActionListener(e -> {
            if (qIndex < 4) qIndex++;
            try {
                displayQuestion(qIndex);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            reset();
        });
        b3.setBackground(Color.WHITE);

        b4 = new JButton("Submit");
        b4.addActionListener(e -> {
            total = 0;
            for (int i = 0; i < 5; i++) {
                if (correctAnswer[i] == userAnswer[i]) total += 10;
            }
            JOptionPane.showMessageDialog(null,
                    "Congratulations: Your Score is: " + total + "/50.");
            try {
                generateQuestion();
                total = 0;
                qIndex = 0;
                displayQuestion(qIndex);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        b4.setBackground(Color.WHITE);

        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.setPreferredSize(new Dimension(350, 150));
    }

    /**
     * Resets the selected radio buttons.
     */
    private void reset() {
        rb1.setSelected(false);
        rb2.setSelected(false);
        rb3.setSelected(false);
        rb4.setSelected(false);
    }

    /**
     * Generates quiz questions.
     *
     * @throws FileNotFoundException if the file containing quiz questions is not found.
     */
    public static void generateQuestion() throws FileNotFoundException {
        ArrayList<QuizQuestion> questions = QuizGenerator.quizMaker();
        qList.clear();
        for (int i = 0; i < 5; i++) {
            int index = generateRandom();
            qList.add(questions.get(index));
        }
    }

    /**
     * Displays the question at the specified index.
     *
     * @param index the index of the question to display.
     * @throws FileNotFoundException if the file containing quiz questions is not found.
     */
    public void displayQuestion(int index) throws FileNotFoundException {
        if (qIndex == 0) b2.setEnabled(false);
        else b2.setEnabled(true);
        if (qIndex == 4) b3.setEnabled(false);
        else b3.setEnabled(true);

        // Display the question
        QuizQuestion a = qList.get(index);
        String[] options = a.getAnswers();
        north.setText("Question " + (qIndex + 1) + " of 5: " + a.getQuestionText());
        rb1.setText(options[0]);
        rb2.setText(options[1]);
        rb3.setText(options[2]);
        rb4.setText(options[3]);
        ca = a.getCorrectAnswer();
        correctAnswer[qIndex] = ca;

        // Set user's answer if previously answered
        int previousAnswer = userAnswer[qIndex];
        switch (previousAnswer) {
            case 0:
                rb1.setSelected(true);
                break;
            case 1:
                rb2.setSelected(true);
                break;
            case 2:
                rb3.setSelected(true);
                break;
            case 3:
                rb4.setSelected(true);
                break;
            default:
                reset();
                break;
        }
    }


    /**
     * Generates a random number.
     *
     * @return the generated random number.
     */
    public static int generateRandom() {
        Random rand = new Random();
        return rand.nextInt(20);
    }

    /**
     * Main method to start the quiz GUI.
     *
     * @param args command line arguments.
     * @throws FileNotFoundException if the file containing quiz questions is not found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        QuizGui q = new QuizGui();
        q.displayQuestion(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JRadioButton) {
            JRadioButton radioButton = (JRadioButton) source;
            if (radioButton == rb1) {
                userAnswer[qIndex] = 0;
            } else if (radioButton == rb2) {
                userAnswer[qIndex] = 1;
            } else if (radioButton == rb3) {
                userAnswer[qIndex] = 2;
            } else if (radioButton == rb4) {
                userAnswer[qIndex] = 3;
            }
        }
    }
}

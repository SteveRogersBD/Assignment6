import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class generates quiz questions by reading them from a file.
 * @author Aniruddha Biswas Atanu
 */
public class QuizGenerator {

    static ArrayList<QuizQuestion> quizGame;

    /**
     * Reads questions from a file and generates a list of QuizQuestion objects.
     *
     * @return ArrayList containing QuizQuestion objects.
     * @throws FileNotFoundException If the specified file is not found.
     */
    public static ArrayList<QuizQuestion> quizMaker() throws FileNotFoundException {
        // Open the file containing quiz questions
        File file = new File("mcq.txt");
        Scanner scanner = new Scanner(file);
        quizGame = new ArrayList<>();

        QuizQuestion a = null;
        int i = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            // Check if the line is a new question indicator
            if (line.charAt(0) == '#') {
                if (a != null) {
                    quizGame.add(a);  // add the completed question before starting a new one
                }
                line = line.substring(1).trim();
                a = new QuizQuestion(line);
                i = 0; // reset index for new question
            } else {
                // Check if the line indicates the correct answer
                if (line.charAt(0) == '>') {
                    line = line.substring(1).trim();
                    a.setCorrectAnswer(i);
                }
                a.addQuestion(i, line);
                i++;
            }
        }
        // Add the last question after the loop
        if (a != null) {
            quizGame.add(a);
        }
        // Close the scanner
        scanner.close();
        return quizGame;
    }

    /**
     * Main method to test the QuizGenerator class.
     *
     * @param args Command line arguments (not used).
     * @throws FileNotFoundException If the specified file is not found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        quizMaker();
        // Display the generated quiz questions
        for (QuizQuestion q : quizGame) {
            System.out.println(q);
        }
    }
}

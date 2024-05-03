import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuizGenerator{

    static ArrayList<QuizQuestion> quizGame;

    /**
     * Reads questions from a file and generates a list of QuizQuestion objects.
     * @return ArrayList containing QuizQuestion objects.
     * @throws FileNotFoundException If the specified file is not found.
     */
    public static ArrayList<QuizQuestion> quizMaker() throws FileNotFoundException {
        File file = new File("mcq.txt");
        Scanner scanner = new Scanner(file);
        quizGame = new ArrayList<>();

        QuizQuestion a = null;
        int i = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.charAt(0) == '#') {
                if (a != null) {
                    quizGame.add(a);  // add the completed question before starting a new one
                }
                line = line.substring(1).trim();
                a = new QuizQuestion(line);
                i = 0; // reset index for new question
            } else {
                if (line.charAt(0) == '>') {
                    line = line.substring(1).trim();
                    a.setCorrectAnswer(i);
                }
                a.addQuestion(i, line);
                i++;
            }
        }
        if (a != null) {
            quizGame.add(a);  // add the last question after the loop
        }
        scanner.close();
        return quizGame;
    }

    /**
     * Main method to test the QuizGenerator class.
     * @param args Command line arguments (not used).
     * @throws FileNotFoundException If the specified file is not found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        quizMaker();
        for (QuizQuestion q : quizGame) {
            System.out.println(q);
        }
    }

}

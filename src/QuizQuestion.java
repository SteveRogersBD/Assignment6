
/**
 * This class represents a single quiz question.
 * @author Aniruddha Biswas Atanu
 */
public class QuizQuestion {
    private String questionText; // The text of the question
    private String answers[];    // Array to store the possible answers
    private int correctAnswer;   // Index of the correct answer
    final int MAX_ANS = 4;       // Maximum number of answers allowed

    /**
     * Constructor to initialize a QuizQuestion object with the question text.
     * @param qt The text of the question.
     */
    QuizQuestion(String qt){
        questionText = qt;
        answers = new String[MAX_ANS];
    }

    /**
     * Method to add an answer to the list of possible answers.
     * @param index The index of the answer.
     * @param answerText The text of the answer.
     */
    void addQuestion(int index, String answerText) {
        answers[index] = answerText;
    }

    /**
     * Method to set the correct answer for the question.
     * @param answerIndex The index of the correct answer.
     */
    void setCorrectAnswer(int answerIndex) {
        correctAnswer = answerIndex;
    }

    /**
     * Method to get the text of the question.
     * @return The text of the question.
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Method to get the array of possible answers.
     * @return The array of possible answers.
     */
    public String[] getAnswers() {
        return answers;
    }

    /**
     * Method to get the index of the correct answer.
     * @return The index of the correct answer.
     */
    public int getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String toString() {
        return questionText+"\n"+answers[0]+"\n"
                +answers[1]+"\n"
                +answers[2]+"\n"
                +answers[3]+"\n"
                +getCorrectAnswer();
    }
}

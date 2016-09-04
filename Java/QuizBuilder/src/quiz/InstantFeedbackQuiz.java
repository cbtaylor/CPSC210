package quiz;

import exceptions.RetryAnswerException;
import questionslist.QuestionsList;

/**
 * Represents a Quiz that provides as much feedback to the user as possible.
 */
public class InstantFeedbackQuiz extends Quiz {

    public InstantFeedbackQuiz(QuestionsList questions) {
        super(questions);
    }

    /**
     * When the user submits an answer, return a string to indicate whether or
     * not the user supplied the right answer, and what their mark so far is.
     */
    @Override
    public String submitAnswer(String answer) throws RetryAnswerException {
        boolean correct = super.checkAnswer(answer);
        String ret = "";
        if (correct) {
            ret += "Correct! Your mark so far is " + this.getMarkSoFar()
                    + " out of " + this.getMaxMark();
        } else {
            ret += "Incorrect! Your mark so far is " + this.getMarkSoFar()
                    + " out of " + this.getMaxMark();
        }
        return ret;
    }

    /**
     * When the user ends the quiz tell them their final mark.
     */
    @Override
    public String endQuiz() {
        String ret = super.endQuiz();
        ret += "\nYou did great!";
        return ret;
    }
}

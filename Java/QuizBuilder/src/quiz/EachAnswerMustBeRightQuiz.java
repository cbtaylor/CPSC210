package quiz;

import exceptions.RetryAnswerException;
import questionslist.QuestionsList;

/**
 * Represents a Quiz that requires the right answer to a question before the
 * user can move on.
 */
public class EachAnswerMustBeRightQuiz extends Quiz {

    public EachAnswerMustBeRightQuiz(QuestionsList questions) {
        super(questions);
    }

    /**
     * Enforce the right answer requirement by throwing a RetryAnswerException
     * exception when the user submits a wrong answer.
     */
    @Override
    public String submitAnswer(String answer) throws RetryAnswerException {
        boolean correct = super.checkAnswer(answer);
        if (!correct) {
            throw new RetryAnswerException("Wrong answer, please retry.");
        }
        return "";
    }
}

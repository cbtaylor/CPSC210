package question;

/**
 * A True/False question.
 */
public class TrueFalseQuestion extends ShortAnswerQuestion {

    public TrueFalseQuestion(int maxMark, String questionString,
            boolean correctAnswer) {
        super(maxMark, questionString, Boolean.toString(correctAnswer));
    }

    @Override
    public String getQuestionString() {
        return "'true' or 'false': " + super.getQuestionString();
    }
}

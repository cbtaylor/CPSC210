package question;

/**
 * A question that has a single, unambiguous, correct answer.
 */
public class ShortAnswerQuestion extends Question {

    String correctAnswer;

    public ShortAnswerQuestion(int maxMark, String questionString,
            String correctAnswer) {
        super(maxMark, questionString);
        this.correctAnswer = correctAnswer;
    }

    
    
    @Override
    public boolean isCorrect(String answer) {
        return answer.equals(correctAnswer);
    }



	@Override
	public String getHint() {
		String firstpart = correctAnswer.substring(0, correctAnswer.length()/2);
		String hint = "The correct answer begins with " + firstpart;
		return hint;
	}
}

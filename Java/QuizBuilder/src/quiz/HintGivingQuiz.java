package quiz;

import questionslist.QuestionsList;
import exceptions.OutOfTriesException;
import exceptions.RetryAnswerException;

public class HintGivingQuiz extends Quiz {

	public HintGivingQuiz(QuestionsList questions) {
		super(questions);
	}

	
    @Override
    public String submitAnswer(String answer) throws RetryAnswerException,
            OutOfTriesException {
        boolean correct = super.checkAnswer(answer);
        if (curQuestion.getMaxMark() <= 1.0)
        	throw new OutOfTriesException("Out of tries! Sorry!");
        else if (!correct) {
        	curQuestion.setMaxMark(curQuestion.getMaxMark() - 1);
        	throw new RetryAnswerException(curQuestion.getHint());
        }
        
        return "";
    }

}

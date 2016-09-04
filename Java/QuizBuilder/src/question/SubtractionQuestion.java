package question;

public class SubtractionQuestion extends Question {
	
	int num1;
	int num2;
	int result;

	public SubtractionQuestion(double maxMark, int num1, int num2) {
		super(maxMark, num1 + " - " + num2 + " = ?");
		this.num1 = num1;
		this.num2 = num2;
		this.result = num1 - num2;
	}

	@Override
	public boolean isCorrect(String answer) {
		try {
			int answerI = Integer.parseInt(answer);
			return (answerI == result);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public String getHint() {
		int lower = result - 5;
		int higher = result + 5;
		String hint = "The answer is between " + lower + " and " + higher;
		return hint;
	}

}

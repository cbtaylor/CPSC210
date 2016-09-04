package quiz;

import exceptions.OutOfTriesException;
import exceptions.RetryAnswerException;
import exceptions.TimeIsUpException;
import question.Question;
import questionslist.QuestionsList;

/**
 * Represent a quiz that has a list of questions and keeps track of an ongoing
 * quiz --- mark so far, the next question to show to the user, etc.
 */
public abstract class Quiz {
    protected QuestionsList questions;

    // The index of the next question to show to the user.
    int nextQuestion;

    // Whether or not this quiz has started.
    boolean quizStarted;

    // Whether or not this quiz has already been taken.
    boolean quizFinished;

    // The user's mark so far.
    int markSoFar;

    // The current question that is displayed to the user.
    Question curQuestion;

    // REQUIRES: questions cannot be an empty list
    public Quiz(QuestionsList questions) {
        assert questions.length() > 0;
        // Set the fields to sensible initial values.
        this.questions = questions;
        this.markSoFar = 0;
        this.quizStarted = false;
        this.quizFinished = false;
        this.nextQuestion = 0;
        this.curQuestion = null;
    }

    /**
     * Returns the mark of the user thus far.
     * <p>
     * REQUIRES: quiz must be ongoing to finished.
     * </p>
     */
    public int getMarkSoFar() {
        assert (this.quizStarted || this.quizFinished);

        return markSoFar;
    }

    /**
     * Returns the maximum mark feasible.
     */
    public int getMaxMark() {
        return questions.getMaxMark();
    }

    /**
     * Starts the quiz.
     */
    public void startQuiz() {
        assert (this.quizStarted == false);
        assert (this.quizFinished == false);

        this.quizStarted = true;
    }

    /**
     * Retrieves the next question.
     * <p>
     * REQUIRES: curQuestion must not be the last question.
     * </p>
     */
    public Question getNextQuestion() {
        assert (nextQuestion < this.questions.length());

        Question ret = questions.getQuestion(nextQuestion);
        this.nextQuestion += 1;
        curQuestion = ret;
        return ret;
    }

    /**
     * Returns whether or not curQuestion is the last question.
     */
    public boolean anymoreQuestions() {
        return (nextQuestion < this.questions.length());
    }

    /**
     * Ends the quiz. Returns a string to display to the user at the end of the
     * quiz.
     */
    public String endQuiz() {
        assert (this.quizStarted == true);
        assert (this.quizFinished == false);

        this.quizStarted = false;
        this.quizFinished = true;
        return "Your final mark is: " + markSoFar;
    }

    /**
     * Checks the answer to the current question and returns true if the answer
     * is correct, and false otherwise.
     */
    public boolean checkAnswer(String answer) {
        if (this.curQuestion.isCorrect(answer)) {
            markSoFar += curQuestion.getMaxMark();
            return true;
        }
        return false;
    }

    /**
     * Submit an answer to the current question. Throws the RetryAnswerException
     * if the user should re-try the question.
     */
    public abstract String submitAnswer(String answer)
            throws RetryAnswerException, TimeIsUpException, OutOfTriesException;

}

package questionslist;

import java.util.LinkedList;
import java.util.List;

import question.*;

/**
 * Represents a list of quiz questions.
 */
public class QuestionsList {
    protected List<Question> questions;

    public QuestionsList() {
        questions = new LinkedList<Question>();
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public void removeLastQuestion() {
        if (!questions.isEmpty()) {
            int lastQuestionIndex = questions.size() - 1;
            questions.remove(lastQuestionIndex);
        }
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }

    public int length() {
        return questions.size();
    }

    /**
     * Returns the max total mark possible as a sum of the marks across all of
     * the questions.
     */
    public int getMaxMark() {
        int maxMark = 0;
        for (Question q : questions) {
            maxMark += q.getMaxMark();
        }
        return maxMark;
    }
}

package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import exceptions.*;
import question.*;
import questionslist.*;
import quiz.*;

/**
 * The class that orchestrates the actual running of the quiz.
 * 
 * Run this class as a "Java Application" and follow instructions on the
 * Console.
 * 
 */
public class QuizRunner {

    /**
     * An initial set of three T/F questions.
     */
    public QuestionsList getTFQuestions() {
        this.print("Welcome to the True/False roller-coaster!\n");

        Question q1 = null, q2 = null, q3 = null;
        //why not make your own qX questions and use them to test??

        q1 = new TrueFalseQuestion(8, "You are awesome.", true);
        q2 = new TrueFalseQuestion(8, "Donuts are bad for you.", true);
        // Un-comment to test SubtractionQuestion:
        q3 = new SubtractionQuestion(8, 16, 4);

        QuestionsList qList = new RandomizedQuestionsList();
        qList.addQuestion(q1);
        qList.addQuestion(q2);
        // Un-comment to test SubtractionQuestion:
        qList.addQuestion(q3);
        
        //why not make your own qX questions and use them to test??
        return qList;
    }

    /**
     * Prints a line to standard out.
     */
    public void print(String s) {
        if (s != null) {
            System.out.println(s);
        }
    }

    /**
     * Reads a line of text from standard input.
     */
    private String getUserResponse() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = null;
        try {
            line = br.readLine();
        } catch (IOException ioe) {
            System.out.println("IO error trying to read a line of text.");
            System.exit(1);
        }
        return line;
    }

    // ///////////////////////////////////////////////////////

    public static void main(String args[]) {

        QuizRunner qRunner = new QuizRunner();

        // TODO: query user for different kinds of questions here.
        QuestionsList qList = qRunner.getTFQuestions();

        // Build the right quiz based on user's input.
        Quiz quiz = null;
        do {
            qRunner.print("Enter a number for the type of quiz:");
            qRunner.print("1 : Instant feedback quiz.");
            qRunner.print("2 : Each answer must be right quiz.");
            qRunner.print("3 : Each wrong answer deducts a mark and gives a hint");
            qRunner.print("4 : A quiz with a timer.");
            qRunner.print("Your response: ");

            String qType = qRunner.getUserResponse();
            if (qType.equals("1")) {
                quiz = new InstantFeedbackQuiz(qList);
            } else if (qType.equals("2")) {
                quiz = new EachAnswerMustBeRightQuiz(qList);
            }
            else if (qType.equals("3")) {
            	quiz = new HintGivingQuiz(qList);
            }
            else if (qType.equals("4")) {

                // How long should the quiz be timed for:
                int numSeconds = 0;
                do {
                    qRunner.print("Enter number of seconds for the quiz:");
                    qRunner.print("Your response: ");
                    Scanner in = new Scanner(System.in);
                    numSeconds = in.nextInt();
                } while (numSeconds < 0);
                quiz = new TimedQuiz(qList, numSeconds);
            }
        } while (quiz == null);

        // Generic quiz runner:
        quiz.startQuiz();
        while (quiz.anymoreQuestions()) {
            Question q = quiz.getNextQuestion();

            boolean retryQuestion;
            do {
                retryQuestion = false;
                qRunner.print(q.getQuestionString());
                qRunner.print("Your response: ");
                String answer = qRunner.getUserResponse();
                qRunner.print("");

                try {
                    String feedback = quiz.submitAnswer(answer);
                    if (!feedback.equals("")) {
                        qRunner.print(feedback);
                    }
                } catch (RetryAnswerException e) {
                    qRunner.print(e.getMessage());
                    retryQuestion = true;
                } catch (TimeIsUpException e) {
                    qRunner.print(e.getMessage());
                    System.exit(0);
                } catch (OutOfTriesException e) {
					qRunner.print(e.getMessage());
				}
            } while (retryQuestion);
        }

        String endOfQuizString = quiz.endQuiz();
        qRunner.print(endOfQuizString);
        qRunner.print("\nThanks for taking the quiz!\n");
    }
}

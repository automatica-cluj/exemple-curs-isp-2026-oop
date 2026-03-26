package com.quizapp.controller;

import com.quizapp.model.Question;
import com.quizapp.model.QuizGame;
import com.quizapp.model.User;
import com.quizapp.service.Leaderboard;
import com.quizapp.service.QuestionLoader;
import com.quizapp.view.ConsoleView;

import java.util.Collections;
import java.util.List;

public class QuizController {
    private ConsoleView view;
    private Leaderboard leaderboard;
    private List<Question> allQuestions;
    private final String questionsFilePath;
    private static final long QUIZ_DURATION_MS = 60 * 1000; // 1 minute

    public QuizController(ConsoleView view, Leaderboard leaderboard, String questionsFilePath) {
        this.view = view;
        this.leaderboard = leaderboard;
        this.questionsFilePath = questionsFilePath;
    }

    public void initializeGame() {
        allQuestions = QuestionLoader.loadQuestionsFromFile(questionsFilePath);
        if (allQuestions == null || allQuestions.isEmpty()) {
            view.displayError("Failed to load questions or no questions found. Exiting.");
            System.exit(1); // Or handle more gracefully
        }
    }

    public void startQuiz() {
        view.displayWelcomeMessage();
        initializeGame(); // Load questions once

        boolean playAgain = true;
        while(playAgain) {
            String userName = view.promptUserName();
            
            if (allQuestions.isEmpty()) {
                view.displayMessage("No questions available to start the quiz.");
                break; 
            }
            // Shuffle questions for each new game session for variety
            List<Question> currentQuizQuestions = new java.util.ArrayList<>(allQuestions);
            Collections.shuffle(currentQuizQuestions);

            QuizGame game = new QuizGame(currentQuizQuestions, userName);
            boolean timeExpired = false;

            while (game.hasNextQuestion() && !game.isTimeUp(QUIZ_DURATION_MS)) {
                Question currentQuestion = game.getCurrentQuestion();
                view.displayQuestion(currentQuestion);
                int answer = view.promptAnswer(currentQuestion.getOptions().size());
                boolean correct = game.answerQuestion(answer);
                view.displayResult(correct);
                if (!correct) {
                    break; // End game on wrong answer
                }
                game.moveToNextQuestion();
            }

            if (game.isTimeUp(QUIZ_DURATION_MS) && game.hasNextQuestion()) { // Check if time ran out before all questions were answered or a wrong answer
                timeExpired = true;
                view.displayTimeUpMessage();
            }

            view.displayGameOver(userName, game.getCurrentScore());
            leaderboard.addScore(userName, game.getCurrentScore());
            view.displayLeaderboard(leaderboard.getAllScoresSorted()); // Display all scores sorted

            playAgain = view.promptPlayAgain();
        }
        view.displayMessage("Thanks for playing!");
        view.closeScanner();
    }
}

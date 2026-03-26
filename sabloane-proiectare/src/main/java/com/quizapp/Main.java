package com.quizapp;

import com.quizapp.controller.QuizController;
import com.quizapp.service.Leaderboard;
import com.quizapp.view.ConsoleView;

public class Main {
    // Define file paths relative to the project root or an accessible location
    // For this example, assuming they are in the same directory as the running jar/class files
    // or a specific path if running from IDE.
    // For the requested path structure, they are at the root of "sabloane-proiectare"
    private static final String QUESTIONS_FILE_PATH = "questions.txt";
    private static final String LEADERBOARD_FILE_PATH = "leaderboard.txt";

    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        Leaderboard leaderboard = Leaderboard.getInstance(LEADERBOARD_FILE_PATH);
        QuizController controller = new QuizController(view, leaderboard, QUESTIONS_FILE_PATH);
        
        controller.startQuiz();
    }
}

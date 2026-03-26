package com.quizapp.view;

import com.quizapp.model.Question;
import com.quizapp.model.User;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ConsoleView {
    private Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayWelcomeMessage() {
        System.out.println("Welcome to the Java Quiz Application!");
    }

    public String promptUserName() {
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();
        while (name == null || name.trim().isEmpty()) {
            System.out.print("Name cannot be empty. Please enter your name: ");
            name = scanner.nextLine();
        }
        return name.trim();
    }

    public void displayQuestion(Question question) {
        if (question == null) return;
        System.out.println("\n" + question.getQuestionText());
        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    public int promptAnswer(int numOptions) {
        System.out.print("Your answer (1-" + numOptions + "): ");
        int choice = -1;
        while (true) {
            try {
                String input = scanner.nextLine();
                choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= numOptions) {
                    return choice - 1; // Convert to 0-based index
                } else {
                    System.out.print("Invalid choice. Please enter a number between 1 and " + numOptions + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    public void displayResult(boolean correct) {
        if (correct) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect.");
        }
    }

    public void displayGameOver(String userName, int score) {
        System.out.println("\nGame Over, " + userName + "!");
        System.out.println("Your final score: " + score);
    }

    public void displayTimeUpMessage() {
        System.out.println("\nTime's up!");
    }

    public void displayLeaderboard(List<User> topScores) {
        System.out.println("\n--- Leaderboard ---");
        if (topScores.isEmpty()) {
            System.out.println("No scores yet.");
        } else {
            int rank = 1;
            for (User user : topScores) {
                System.out.println(rank++ + ". " + user.getName() + " - " + user.getScore() + " points");
            }
        }
        System.out.println("-------------------");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
    
    public void displayError(String message) {
        System.err.println(message);
    }

    public boolean promptPlayAgain() {
        System.out.print("\nPlay again? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }

    public void closeScanner() {
        scanner.close();
    }
}

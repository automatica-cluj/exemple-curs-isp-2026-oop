package com.quizapp.service;

import com.quizapp.model.Question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionLoader {
    private static final int LINES_PER_QUESTION_BLOCK = 6; // Question, 4 options, 1 correct answer

    public static List<Question> loadQuestionsFromFile(String filePath) {
        List<Question> questions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<String> currentQuestionBlock = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) { // Separator between questions or end of block
                    if (currentQuestionBlock.size() == LINES_PER_QUESTION_BLOCK) {
                        addQuestionFromBlock(questions, currentQuestionBlock);
                    }
                    currentQuestionBlock.clear();
                } else {
                    currentQuestionBlock.add(line);
                }
            }
            // Process the last block if file doesn't end with a blank line
            if (currentQuestionBlock.size() == LINES_PER_QUESTION_BLOCK) {
                addQuestionFromBlock(questions, currentQuestionBlock);
            }
        } catch (IOException e) {
            System.err.println("Error loading questions from file: " + filePath + " - " + e.getMessage());
            // Return empty list or throw custom exception
        }
        return questions;
    }

    private static void addQuestionFromBlock(List<Question> questions, List<String> block) {
        String questionText = block.get(0);
        List<String> options = new ArrayList<>(block.subList(1, 5));
        String correctAnswerLetter = block.get(5).toUpperCase();
        int correctOptionIndex = -1;
        switch (correctAnswerLetter) {
            case "A": correctOptionIndex = 0; break;
            case "B": correctOptionIndex = 1; break;
            case "C": correctOptionIndex = 2; break;
            case "D": correctOptionIndex = 3; break;
            default:
                System.err.println("Warning: Invalid correct answer letter '" + correctAnswerLetter + "' for question: " + questionText);
                return; // Skip this question
        }
        questions.add(new Question(questionText, options, correctOptionIndex));
    }
}

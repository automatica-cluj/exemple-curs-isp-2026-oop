package com.quizapp.service;

import com.quizapp.model.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Leaderboard {
    private static Leaderboard instance;
    private Map<String, Integer> scores; // Username -> Highest Score
    private final String leaderboardFilePath;

    private Leaderboard(String filePath) {
        this.leaderboardFilePath = filePath;
        this.scores = new HashMap<>();
        loadLeaderboard();
    }

    public static synchronized Leaderboard getInstance(String filePath) {
        if (instance == null) {
            instance = new Leaderboard(filePath);
        }
        return instance;
    }

    private void loadLeaderboard() {
        if (!Files.exists(Paths.get(leaderboardFilePath))) {
            return; // No leaderboard file yet
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(leaderboardFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    try {
                        scores.put(parts[0], Integer.parseInt(parts[1]));
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping malformed line in leaderboard: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading leaderboard: " + e.getMessage());
        }
    }

    private void saveLeaderboard() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(leaderboardFilePath))) {
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving leaderboard: " + e.getMessage());
        }
    }

    public void addScore(String userName, int score) {
        scores.put(userName, Math.max(scores.getOrDefault(userName, 0), score));
        saveLeaderboard();
    }

    public List<User> getTopScores(int count) {
        return scores.entrySet().stream()
                .map(entry -> new User(entry.getKey(), entry.getValue()))
                .sorted() // Uses User.compareTo for descending score
                .limit(count)
                .collect(Collectors.toList());
    }

    public List<User> getAllScoresSorted() {
         return scores.entrySet().stream()
                .map(entry -> new User(entry.getKey(), entry.getValue()))
                .sorted()
                .collect(Collectors.toList());
    }
}

package model;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private String password;
    private int currentLevel;
    private Map<String, Integer> lessonScores;
    private Map<String, Integer> lessonAttempts;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.currentLevel = 1;
        this.lessonScores = new HashMap<>();
        this.lessonAttempts = new HashMap<>();
    }
    
    public boolean login(String enteredPassword) {
        return password.equals(enteredPassword);
    }
    
    public void updateLessonScore(String lessonId, int score) {
        lessonScores.put(lessonId, score);
        if (score >= 70) {
            currentLevel = Math.max(currentLevel, getLessonDifficulty(lessonId) + 1);
        }
    }
    
    public int getLessonAttempts(String lessonId) {
        return lessonAttempts.getOrDefault(lessonId, 0);
    }
    
    public void incrementLessonAttempt(String lessonId) {
        lessonAttempts.put(lessonId, getLessonAttempts(lessonId) + 1);
    }
    
    public boolean hasCompletedLesson(String lessonId) {
        return lessonScores.containsKey(lessonId) && lessonScores.get(lessonId) >= 70;
    }
    
    public int getCurrentLevel() {
        return currentLevel;
    }
    
    public int getScoreForLesson(String lessonId) {
        return lessonScores.getOrDefault(lessonId, 0);
    }
    
    private int getLessonDifficulty(String lessonId) {
        // Extract difficulty level from lesson ID (e.g., "G1" -> 1, "V2" -> 2)
        return Integer.parseInt(lessonId.substring(1));
    }
    
    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
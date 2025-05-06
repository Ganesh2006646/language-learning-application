package model;

import java.util.ArrayList;
import java.util.List;

public class LessonProgress implements core.Trackable {
    private User user;
    private List<String> completedLessons;
    private List<String> unlockedLessons;
    private int dailyGoal;
    private int dailyProgress;
    
    public LessonProgress(User user) {
        this.user = user;
        this.completedLessons = new ArrayList<>();
        this.unlockedLessons = new ArrayList<>();
        this.dailyGoal = 3; // Default daily goal
        this.dailyProgress = 0;
    }
    
    @Override
    public void updateProgress(String lessonId, int score) {
        if (score >= 70 && !completedLessons.contains(lessonId)) {
            completedLessons.add(lessonId);
            dailyProgress++;
        }
    }
    
    @Override
    public boolean hasCompletedPrerequisite(String lessonId) {
        // Check if user has completed the previous level lesson
        String prefix = lessonId.substring(0, 1);
        int level = Integer.parseInt(lessonId.substring(1));
        
        if (level == 1) return true; // No prerequisite for level 1
        
        String prerequisiteId = prefix + (level - 1);
        return completedLessons.contains(prerequisiteId);
    }
    
    @Override
    public boolean canAccessLesson(String lessonId) {
        return hasCompletedPrerequisite(lessonId) && 
               user.getCurrentLevel() >= getLessonLevel(lessonId);
    }
    
    @Override
    public int getCurrentLevel() {
        return user.getCurrentLevel();
    }
    
    public boolean hasMetDailyGoal() {
        return dailyProgress >= dailyGoal;
    }
    
    public void resetDailyProgress() {
        dailyProgress = 0;
    }
    
    public String getProgressReport() {
        return String.format("Completed Lessons: %d/%d | Daily Progress: %d/%d",
                completedLessons.size(), unlockedLessons.size(), dailyProgress, dailyGoal);
    }
    
    private int getLessonLevel(String lessonId) {
        return Integer.parseInt(lessonId.substring(1));
    }
}
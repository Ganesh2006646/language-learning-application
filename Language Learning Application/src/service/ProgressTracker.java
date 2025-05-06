package service;

import model.User;
import model.LessonProgress;
import java.util.List;

public class ProgressTracker {
    private User user;
    private LessonProgress progress;
    
    public ProgressTracker(User user) {
        this.user = user;
        this.progress = new LessonProgress(user);
    }
    
    public String getProgressSummary() {
        return progress.getProgressReport();
    }
    
    public void displayCompletedLessons() {
        System.out.println("\n=== Completed Lessons ===");
        // In a real implementation, this would list completed lessons
        System.out.println("Total completed: " + progress.getCurrentLevel() + " levels");
    }
    
    public void displayDailyProgress() {
        System.out.println("\n=== Daily Progress ===");
        System.out.println("Completed today: " + progress.getCurrentLevel());
        if (progress.hasMetDailyGoal()) {
            System.out.println("Congratulations! You've met your daily goal!");
        } else {
            System.out.println("Keep going! You're " + 
                (progress.getCurrentLevel() - progress.getCurrentLevel()) + 
                " lessons away from your daily goal.");
        }
    }
    
    public void resetDailyProgress() {
        progress.resetDailyProgress();
    }
}
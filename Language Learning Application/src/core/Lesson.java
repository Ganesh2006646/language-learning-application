package core;

public abstract class Lesson {
    private String lessonId;
    private String title;
    private String content;
    private boolean isCompleted;
    private int difficultyLevel;
    
    public Lesson(String lessonId, String title, String content, int difficultyLevel) {
        this.lessonId = lessonId;
        this.title = title;
        this.content = content;
        this.difficultyLevel = difficultyLevel;
        this.isCompleted = false;
    }
    
    // Abstract methods
    public abstract void displayLesson();
    public abstract String[] getQuestions();
    
    // Common methods
    public void markAsCompleted() {
        this.isCompleted = true;
    }
    
    // Getters
    public String getLessonId() { return lessonId; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public boolean isCompleted() { return isCompleted; }
    public int getDifficultyLevel() { return difficultyLevel; }
}
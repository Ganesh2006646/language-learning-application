package core;

public interface Trackable {
    void updateProgress(String lessonId, int score);
    boolean hasCompletedPrerequisite(String lessonId);
    boolean canAccessLesson(String lessonId);
    int getCurrentLevel();
}
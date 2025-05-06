package core;

public interface Assessable {
    void startAssessment();
    int calculateScore(String[] userAnswers);
    boolean canRetakeAssessment(int attempts);
    int getMaxAttempts();
    int getPassingScore();
}
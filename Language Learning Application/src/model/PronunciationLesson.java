package model;

import core.Lesson;
import core.Assessable;

public class PronunciationLesson extends Lesson implements Assessable {
    private String[] phrases;
    private String[] phoneticSpellings;
    private String[] questions;
    private int attempts;
    
    public PronunciationLesson(String lessonId, String title, String content, int difficultyLevel,
                             String[] phrases, String[] phoneticSpellings, String[] questions) {
        super(lessonId, title, content, difficultyLevel);
        this.phrases = phrases;
        this.phoneticSpellings = phoneticSpellings;
        this.questions = questions;
        this.attempts = 0;
    }
    
    @Override
    public void displayLesson() {
        System.out.println("\n=== Pronunciation Lesson: " + getTitle() + " ===");
        System.out.println(getContent());
        System.out.println("\nPhrases to Practice:");
        for (int i = 0; i < phrases.length; i++) {
            System.out.println("- " + phrases[i] + " (" + phoneticSpellings[i] + ")");
        }
    }
    
    @Override
    public String[] getQuestions() {
        return questions;
    }
    
    @Override
    public void startAssessment() {
        System.out.println("\n=== Pronunciation Assessment ===");
        System.out.println("Speak the following phrases clearly:");
        for (int i = 0; i < questions.length; i++) {
            System.out.println((i+1) + ". " + questions[i]);
        }
        System.out.println("\n(Note: This is a simulated assessment. In a real app, this would use speech recognition.)");
        attempts++;
    }
    
    @Override
    public int calculateScore(String[] userAnswers) {
        // Simulate pronunciation scoring
        return (int)(Math.random() * 30) + 70; // Random score between 70-100 for simulation
    }
    
    @Override
    public boolean canRetakeAssessment(int attempts) {
        return attempts < getMaxAttempts();
    }
    
    @Override
    public int getMaxAttempts() {
        return 1;
    }
    
    @Override
    public int getPassingScore() {
        return 80;
    }
}
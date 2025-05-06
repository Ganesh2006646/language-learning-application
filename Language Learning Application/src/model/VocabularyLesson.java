package model;

import core.Lesson;
import core.Assessable;

public class VocabularyLesson extends Lesson implements Assessable {
    private String[] words;
    private String[] definitions;
    private String[] questions;
    private String[] correctAnswers;
    private int attempts;
    
    public VocabularyLesson(String lessonId, String title, String content, int difficultyLevel,
                          String[] words, String[] definitions, String[] questions, String[] correctAnswers) {
        super(lessonId, title, content, difficultyLevel);
        this.words = words;
        this.definitions = definitions;
        this.questions = questions;
        this.correctAnswers = correctAnswers;
        this.attempts = 0;
    }
    
    @Override
    public void displayLesson() {
        System.out.println("\n=== Vocabulary Lesson: " + getTitle() + " ===");
        System.out.println(getContent());
        System.out.println("\nWords to Learn:");
        for (int i = 0; i < words.length; i++) {
            System.out.println("- " + words[i] + ": " + definitions[i]);
        }
    }
    
    @Override
    public String[] getQuestions() {
        return questions;
    }
    
    @Override
    public void startAssessment() {
        System.out.println("\n=== Vocabulary Assessment ===");
        for (int i = 0; i < questions.length; i++) {
            System.out.println("\nQ" + (i+1) + ": " + questions[i]);
        }
        attempts++;
    }
    
    @Override
    public int calculateScore(String[] userAnswers) {
        int score = 0;
        for (int i = 0; i < correctAnswers.length; i++) {
            if (i < userAnswers.length && userAnswers[i].equalsIgnoreCase(correctAnswers[i])) {
                score++;
            }
        }
        return (score * 100) / correctAnswers.length;
    }
    
    @Override
    public boolean canRetakeAssessment(int attempts) {
        return attempts < getMaxAttempts();
    }
    
    @Override
    public int getMaxAttempts() {
        return 2;
    }
    
    @Override
    public int getPassingScore() {
        return 75;
    }
}
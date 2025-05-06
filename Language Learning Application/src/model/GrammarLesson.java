package model;

import core.Lesson;
import core.Assessable;

public class GrammarLesson extends Lesson implements Assessable {
    private String[] questions;
    private String[][] options;
    private String[] correctAnswers;
    private int attempts;
    
    public GrammarLesson(String lessonId, String title, String content, int difficultyLevel,
                        String[] questions, String[][] options, String[] correctAnswers) {
        super(lessonId, title, content, difficultyLevel);
        this.questions = questions;
        this.options = options;
        this.correctAnswers = correctAnswers;
        this.attempts = 0;
    }
    
    @Override
    public void displayLesson() {
        System.out.println("\n=== Grammar Lesson: " + getTitle() + " ===");
        System.out.println(getContent());
        System.out.println("\nKey Concepts:");
        for (String question : questions) {
            System.out.println("- " + question);
        }
    }
    
    @Override
    public String[] getQuestions() {
        return questions;
    }
    
    @Override
    public void startAssessment() {
        System.out.println("\n=== Grammar Assessment ===");
        for (int i = 0; i < questions.length; i++) {
            System.out.println("\nQ" + (i+1) + ": " + questions[i]);
            for (int j = 0; j < options[i].length; j++) {
                System.out.println((j+1) + ". " + options[i][j]);
            }
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
        return 3;
    }
    
    @Override
    public int getPassingScore() {
        return 70;
    }
}
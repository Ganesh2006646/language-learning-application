package service;

import core.Assessable;
import core.Lesson;
import model.*;
import java.util.*;

public class LessonManager {
    private Map<String, Lesson> lessons;
    private User currentUser;
    private LessonProgress progress;
    
    public LessonManager(User user) {
        this.currentUser = user;
        this.progress = new LessonProgress(user);
        this.lessons = new HashMap<>();
        initializeLessons();
    }
    
    private void initializeLessons() {
        // Grammar Lessons
        lessons.put("G1", new GrammarLesson("G1", "Basic Sentence Structure", 
            "Learn how to form basic sentences.", 1,
            new String[]{"What is the correct word order?", "Which is the correct verb form?"},
            new String[][]{
                {"Subject-Verb-Object", "Object-Subject-Verb", "Verb-Object-Subject"},
                {"go", "goes", "going"}
            },
            new String[]{"1", "2"}
        ));
        
        lessons.put("G2", new GrammarLesson("G2", "Past Tense", 
            "Learn how to use past tense verbs.", 2,
            new String[]{"What is the past tense of 'go'?", "Which sentence is correct?"},
            new String[][]{
                {"goed", "went", "gone"},
                {"I eat breakfast yesterday", "I eaten breakfast yesterday", "I ate breakfast yesterday"}
            },
            new String[]{"2", "3"}
        ));
        
        // Vocabulary Lessons
        lessons.put("V1", new VocabularyLesson("V1", "Food Vocabulary", 
            "Learn common food-related words.", 1,
            new String[]{"apple", "banana", "bread"},
            new String[]{"a sweet fruit", "a yellow fruit", "made from flour"},
            new String[]{"What is a sweet fruit?", "What is made from flour?"},
            new String[]{"apple", "bread"}
        ));
        
        lessons.put("V2", new VocabularyLesson("V2", "Clothing Vocabulary", 
            "Learn common clothing items.", 2,
            new String[]{"shirt", "pants", "shoes"},
            new String[]{"worn on upper body", "worn on legs", "worn on feet"},
            new String[]{"What do you wear on your feet?", "What do you wear on your legs?"},
            new String[]{"shoes", "pants"}
        ));
        
        // Pronunciation Lessons
        lessons.put("P1", new PronunciationLesson("P1", "Basic Pronunciation", 
            "Practice basic pronunciation.", 1,
            new String[]{"Hello", "Goodbye", "Thank you"},
            new String[]{"heh-LOH", "good-BYE", "THANK yoo"},
            new String[]{"Say 'Hello'", "Say 'Goodbye'", "Say 'Thank you'"}
        ));
    }
    
    public List<Lesson> getAvailableLessons() {
        List<Lesson> availableLessons = new ArrayList<>();
        for (Lesson lesson : lessons.values()) {
            if (progress.canAccessLesson(lesson.getLessonId())) {
                availableLessons.add(lesson);
            }
        }
        return availableLessons;
    }
    
    public Lesson getLesson(String lessonId) {
        return lessons.get(lessonId);
    }
    
    public void startLesson(String lessonId) {
        Lesson lesson = lessons.get(lessonId);
        if (lesson != null && progress.canAccessLesson(lessonId)) {
            lesson.displayLesson();
        } else {
            System.out.println("Lesson not available or not unlocked yet.");
        }
    }
    
    public void takeAssessment(String lessonId) {
        Lesson lesson = lessons.get(lessonId);
        if (lesson instanceof Assessable) {
            Assessable assessable = (Assessable) lesson;
            
            if (!assessable.canRetakeAssessment(currentUser.getLessonAttempts(lessonId))) {
                System.out.println("You've reached the maximum attempts for this assessment.");
                return;
            }
            
            assessable.startAssessment();
            currentUser.incrementLessonAttempt(lessonId);
            
            // Simulate user input
            String[] userAnswers = new String[lesson.getQuestions().length];
            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < userAnswers.length; i++) {
                System.out.print("Answer for Q" + (i+1) + ": ");
                userAnswers[i] = scanner.nextLine();
            }
            
            int score = assessable.calculateScore(userAnswers);
            System.out.println("Your score: " + score + "%");
            
            if (score >= assessable.getPassingScore()) {
                System.out.println("Congratulations! You passed this assessment.");
                currentUser.updateLessonScore(lessonId, score);
                progress.updateProgress(lessonId, score);
            } else {
                System.out.println("You didn't pass. Minimum required score: " + 
                    assessable.getPassingScore() + "%");
            }
        }
    }
    
    public boolean hasMetDailyGoal() {
        return progress.hasMetDailyGoal();
    }
}
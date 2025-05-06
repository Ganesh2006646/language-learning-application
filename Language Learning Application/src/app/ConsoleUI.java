package app;

import model.User;
import service.LessonManager;
import service.ProgressTracker;
import util.ValidationUtil;

import java.util.Scanner;

public class ConsoleUI {
    private Scanner scanner;
    private User currentUser;
    private LessonManager lessonManager;
    private ProgressTracker progressTracker;
    
    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
    }
    
    public void start() {
        System.out.println("Welcome to Language Learning App!");
        
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("\n1. login");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    register();
                    break;
                case "2":
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        mainMenu();
    }
    
    private void register() {
        System.out.print("\nChoose a username: ");
        String username = scanner.nextLine();
        System.out.print("Choose a password: ");
        String password = scanner.nextLine();
        
        if (ValidationUtil.validateUsername(username) && ValidationUtil.validatePassword(password)) {
            this.currentUser = new User(username, password);
            this.lessonManager = new LessonManager(currentUser);
            this.progressTracker = new ProgressTracker(currentUser);
            System.out.println("You are now logged in.");
            mainMenu();
        } else {
            System.out.println("Invalid username or password. Username must be at least 4 characters, " +
                             "password at least 6 characters.");
        }
    }
    
    private void mainMenu() {
        boolean running = true;
        
        while (running) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. View Available Lessons");
            System.out.println("2. Start a Lesson");
            System.out.println("3. Take an Assessment");
            System.out.println("4. View Progress");
            System.out.println("5. View Daily Challenge");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    listAvailableLessons();
                    break;
                case "2":
                    startLesson();
                    break;
                case "3":
                    takeAssessment();
                    break;
                case "4":
                    viewProgress();
                    break;
                case "5":
                    viewDailyChallenge();
                    break;
                case "6":
                    running = false;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private void listAvailableLessons() {
        System.out.println("\n=== Available Lessons ===");
        lessonManager.getAvailableLessons().forEach(lesson -> {
            System.out.println(lesson.getLessonId() + ": " + lesson.getTitle() + 
                             " (Level " + lesson.getLessonId().substring(1) + ")");
        });
    }
    
    private void startLesson() {
        System.out.print("\nEnter lesson ID to start: ");
        String lessonId = scanner.nextLine();
        lessonManager.startLesson(lessonId);
    }
    
    private void takeAssessment() {
        System.out.print("\nEnter lesson ID to take assessment: ");
        String lessonId = scanner.nextLine();
        lessonManager.takeAssessment(lessonId);
    }
    
    private void viewProgress() {
        progressTracker.displayCompletedLessons();
        System.out.println(progressTracker.getProgressSummary());
    }
    
    private void viewDailyChallenge() {
        progressTracker.displayDailyProgress();
        if (lessonManager.hasMetDailyGoal()) {
            System.out.println("You've completed your daily challenge!");
        } else {
            System.out.println("Complete more lessons to meet your daily goal!");
        }
    }
}
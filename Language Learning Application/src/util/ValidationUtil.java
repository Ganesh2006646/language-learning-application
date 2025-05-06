package util;

public class ValidationUtil {
    public static boolean validateUsername(String username) {
        return username != null && username.length() >= 4 && username.matches("[a-zA-Z0-9]+");
    }
    
    public static boolean validatePassword(String password) {
        return password != null && password.length() >= 6;
    }
    
    public static boolean validateAnswerFormat(String answer, int maxOptions) {
        try {
            int choice = Integer.parseInt(answer);
            return choice >= 1 && choice <= maxOptions;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
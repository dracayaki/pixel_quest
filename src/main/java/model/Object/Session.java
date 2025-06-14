package model.Object;

public class Session {
    private static User currentUser;
    private static int currentLevel;

    public static void setCurrentUser(User currentUser) {
        Session.currentUser = currentUser;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentLevel(int level) {
        currentLevel = level;
    }

    public static int getCurrentLevel() {
        return currentLevel;
    }
}

public class SystemSettings {
    public static final String DEFAULT_LANGUAGE = "English";
    public static final int MAX_STUDENTS = 100;
    public static final int TIME_OUT = 30;
    public static String CURRENT = DEFAULT_LANGUAGE;
    private static int current_users=0;

    public static void printSettings() {
        System.out.println("Language: " + CURRENT);
        System.out.println("Student count: " + current_users);
        System.out.println("Time out: " + TIME_OUT);
        System.out.println("Max students: " + MAX_STUDENTS);
    }

    public static void Main(String[] args) {
       SystemSettings.printSettings();
    }
}

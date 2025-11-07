public class AppConfig {

    public static final String APP_NAME="App_Config";
    public static final String APP_VERSION="1.0.0";
    public static final String AUTHOR="Desislava";

    public static void printInfo(){
        System.out.println(APP_NAME+" "+APP_VERSION);
        System.out.println(AUTHOR);
    }

    public static void main(String[] args) {
        printInfo();
    }
}

package ex10;

public class Main10 {
    public static void main(String[] args) {
        LogSys logger = new LogSys();

        try {
            logger.addLog("Системата стартира.");
            logger.addLog("Потребителят се вписа успешно.");
            logger.addLog("Извършено е търсене в базата данни.");
            logger.addLog("Грешка при четене на файл.");
            logger.addLog("Файлът е успешно записан.");
            logger.addLog("Сесията е изтекла.");
            logger.addLog("Потребителят се отписа.");
            logger.addLog("Системата се изключва.");
            logger.addLog("Рестарт на системата.");
            logger.addLog("Лог тест №10.");
            logger.addLog("Това е 11-тото съобщение");

            logger.printLogs();

        } catch (InvalidLogException e) {
            System.out.println("Грешка: " + e.getMessage());
        }
    }
}

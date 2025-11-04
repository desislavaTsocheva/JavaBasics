package ex10;


import java.util.LinkedList;

public class LogSys {
    private LinkedList<LogEntry> logs = new LinkedList<>();
    private final int MAX_LOGS = 10;

    public void addLog(String message) throws InvalidLogException {
        if (message == null || message.trim().isEmpty()) {
            throw new InvalidLogException("Лог съобщението не може да е празно или null!");
        }

        if (logs.size() >= MAX_LOGS) {
            logs.removeFirst();
        }

        logs.addLast(new LogEntry(message));
    }

    public void printLogs() {
        if (logs.isEmpty()) {
            System.out.println("Няма налични логове.");
            return;
        }

        System.out.println("Текущи логове:");
        for (LogEntry log : logs) {
            System.out.println(log);
        }
    }
}


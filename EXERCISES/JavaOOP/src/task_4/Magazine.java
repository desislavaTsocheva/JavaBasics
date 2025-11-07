package task_4;

public class Magazine extends LibraryItem {
    private String issueNumber;

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    @Override
    public void displayInfo() {
        System.out.println("The issueNumber is: " + issueNumber);
    }
}

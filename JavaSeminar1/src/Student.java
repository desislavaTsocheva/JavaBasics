public class Student {
    private String name;
    private String facultyNumber;
    private static int totalStudents=0;

    public Student(String name, String facultyNumber) {
        this.name = name;
        this.facultyNumber = facultyNumber;
        totalStudents++;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public static void main(String[] args) {
        Student s1 = new Student("Aleksandur", "24578");
        Student s2 = new Student("Desislava", "658944");

        System.out.println(totalStudents);
    }
}

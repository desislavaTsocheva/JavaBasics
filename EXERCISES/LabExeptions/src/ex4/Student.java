package ex4;

import java.util.ArrayList;
import java.util.Scanner;

public class Student {
    private String name;
    private String facNumber;
    private String course;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacNumber() {
        return facNumber;
    }

    public void setFacNumber(String facNumber) {
        this.facNumber = facNumber;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Student(String name, String facNumber, String course) {
        this.name = name;
        this.facNumber = facNumber;
        this.course = course;
    }
    public Student() {

    }

    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student s) throws FacNumberDublication {
        for (Student st : students) {
            if (st.getFacNumber().equals(s.getFacNumber())) {
                throw new FacNumberDublication(
                        "Студент с факултетен номер " + s.getFacNumber() + " вече съществува!"
                );
            }
        }
        students.add(s);
    }

    public void showAllStudents() {
        if (students.isEmpty()) {
            System.out.println("Няма регистрирани студенти.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }
}

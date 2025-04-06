import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String id;
    private List<Double> grades;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.grades = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<Double> getGrades() {
        return grades;
    }

    public void addGrade(double grade) {
        grades.add(grade);
    }

    public double getAverageGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        for (double grade : grades) {
            sum += grade;
        }
        
        return sum / grades.size();
    }

    public double getHighestGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        
        double highest = grades.get(0);
        for (double grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        
        return highest;
    }

    public double getLowestGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        
        double lowest = grades.get(0);
        for (double grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        
        return lowest;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", average grade=" + String.format("%.2f", getAverageGrade()) +
                '}';
    }
}
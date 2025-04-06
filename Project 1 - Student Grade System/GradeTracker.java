import java.util.ArrayList;
import java.util.List;

public class GradeTracker {
    private List<Student> students;

    public GradeTracker() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public double getClassAverage() {
        if (students.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        for (Student student : students) {
            sum += student.getAverageGrade();
        }
        
        return sum / students.size();
    }

    public double getHighestClassGrade() {
        if (students.isEmpty()) {
            return 0.0;
        }
        
        double highest = students.get(0).getHighestGrade();
        for (Student student : students) {
            double studentHighest = student.getHighestGrade();
            if (studentHighest > highest) {
                highest = studentHighest;
            }
        }
        
        return highest;
    }

    public double getLowestClassGrade() {
        if (students.isEmpty()) {
            return 0.0;
        }
        
        double lowest = students.get(0).getLowestGrade();
        for (Student student : students) {
            double studentLowest = student.getLowestGrade();
            if (studentLowest < lowest && studentLowest > 0) {
                lowest = studentLowest;
            }
        }
        
        return lowest;
    }
}
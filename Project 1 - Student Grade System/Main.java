import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static GradeTracker gradeTracker = new GradeTracker();

    public static void main(String[] args) {
        boolean running = true;
        
        System.out.println("Welcome to Student Grade Tracker");
        
        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add a new student");
            System.out.println("2. Add grades for a student");
            System.out.println("3. View student details");
            System.out.println("4. View class statistics");
            System.out.println("5. List all students");
            System.out.println("6. Exit");
            
            System.out.print("\nEnter your choice: ");
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addGrades();
                    break;
                case 3:
                    viewStudentDetails();
                    break;
                case 4:
                    viewClassStatistics();
                    break;
                case 5:
                    listAllStudents();
                    break;
                case 6:
                    running = false;
                    System.out.println("Thank you for using Student Grade Tracker!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        
        // Check if student ID already exists
        if (gradeTracker.findStudentById(id) != null) {
            System.out.println("A student with this ID already exists.");
            return;
        }
        
        Student student = new Student(name, id);
        gradeTracker.addStudent(student);
        System.out.println("Student added successfully!");
    }
    
    private static void addGrades() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        
        Student student = gradeTracker.findStudentById(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        
        System.out.println("Adding grades for " + student.getName());
        System.out.print("How many grades do you want to add? ");
        int count = getIntInput();
        
        for (int i = 0; i < count; i++) {
            System.out.print("Enter grade " + (i + 1) + ": ");
            double grade = getDoubleInput();
            
            if (grade < 0 || grade > 100) {
                System.out.println("Grade must be between 0 and 100. Please try again.");
                i--; // Retry this grade
            } else {
                student.addGrade(grade);
            }
        }
        
        System.out.println("Grades added successfully!");
    }
    
    private static void viewStudentDetails() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        
        Student student = gradeTracker.findStudentById(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        
        System.out.println("\nStudent Details:");
        System.out.println("Name: " + student.getName());
        System.out.println("ID: " + student.getId());
        
        if (student.getGrades().isEmpty()) {
            System.out.println("No grades recorded yet.");
        } else {
            System.out.println("Grades: " + student.getGrades());
            System.out.println("Average Grade: " + String.format("%.2f", student.getAverageGrade()));
            System.out.println("Highest Grade: " + String.format("%.2f", student.getHighestGrade()));
            System.out.println("Lowest Grade: " + String.format("%.2f", student.getLowestGrade()));
        }
    }
    
    private static void viewClassStatistics() {
        if (gradeTracker.getAllStudents().isEmpty()) {
            System.out.println("No students in the system yet.");
            return;
        }
        
        System.out.println("\nClass Statistics:");
        System.out.println("Number of Students: " + gradeTracker.getAllStudents().size());
        System.out.println("Class Average: " + String.format("%.2f", gradeTracker.getClassAverage()));
        System.out.println("Highest Grade in Class: " + String.format("%.2f", gradeTracker.getHighestClassGrade()));
        System.out.println("Lowest Grade in Class: " + String.format("%.2f", gradeTracker.getLowestClassGrade()));
    }
    
    private static void listAllStudents() {
        if (gradeTracker.getAllStudents().isEmpty()) {
            System.out.println("No students in the system yet.");
            return;
        }
        
        System.out.println("\nAll Students:");
        for (Student student : gradeTracker.getAllStudents()) {
            System.out.println(student);
        }
    }
    
    private static int getIntInput() {
        int input = 0;
        boolean valid = false;
        
        while (!valid) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
        
        return input;
    }
    
    private static double getDoubleInput() {
        double input = 0.0;
        boolean valid = false;
        
        while (!valid) {
            try {
                input = Double.parseDouble(scanner.nextLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
        
        return input;
    }
}
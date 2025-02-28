import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagementApp {
    private static final String FILENAME = "employees.ser";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = loadEmployees();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    // Add an Employee
                    System.out.print("Enter Employee Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Employee ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Designation: ");
                    String designation = scanner.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline

                    Employee employee = new Employee(name, id, designation, salary);
                    employees.add(employee);
                    saveEmployees(employees);
                    System.out.println("Employee added successfully!");
                    break;

                case 2:
                    // Display All Employees
                    System.out.println("Employee List:");
                    for (Employee emp : employees) {
                        System.out.println(emp);
                    }
                    break;

                case 3:
                    // Exit
                    System.out.println("Exiting the application.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void saveEmployees(List<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            System.err.println("Error saving employees: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();
        File file = new File(FILENAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                employees = (List<Employee>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading employees: " + e.getMessage());
            }
        }
        return employees;
    }
}

package entity;

import java.util.Scanner;

public class Employee {
    private String empName;
    private String empId;
    private int age;
    private double salary;

    public Employee(String empName, String empId, int age, double salary) {
        this.empName = empName;
        this.empId = empId;
        this.age = age;
        this.salary = salary;
    }

    public Employee() {
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // cho phép nhập đầy đủ thông tin của nhân viên từ bàn phím và validate nếu nhập sai yêu cầu nhập lại
    public void inputData(Scanner scanner) {
        System.out.print("Enter employee name: ");
        empName = scanner.nextLine();
        System.out.print("Enter employee ID: ");
        empId = scanner.nextLine();
        while (true) {
            System.out.print("Enter employee age: ");
            age = Integer.parseInt(scanner.nextLine());
            if (age >= 18) {
                break;
            } else {
                System.out.println("Bạn chưa đủ tuổi");
            }
        }
        while (true) {
            System.out.print("Enter employee salary: ");
            salary = Double.parseDouble(scanner.nextLine());
            if (salary >= 0) {
                break;
            } else {
                System.out.println("Invalid salary. Please enter a non-negative number.");
            }
        }
    }

    // hiển thị thông tin của nhân viên dưới dạng rõ ràng dạng bảng
    public void displayData(){
        System.out.println("entity.Employee Information:");
        System.out.println("---------------------");
        System.out.printf("%-15s: %s%n", "Name", empName);
        System.out.printf("%-15s: %s%n", "ID", empId);
        System.out.printf("%-15s: %d%n", "Age", age);
        System.out.printf("%-15s: %.2f%n", "Salary", salary);
        System.out.println("---------------------");

    }
}

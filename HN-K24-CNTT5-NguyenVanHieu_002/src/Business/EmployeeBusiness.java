package Business;

import entity.Employee;

import java.util.List;
import java.util.Scanner;

// quản lý 1 danh sách List<entity.Employee>
// đối tượng Business.EmployeeBusiness chỉ được tồn tại duy nhất 1 instance trong chương trình (singleton pattern)
// các chức năng thao tác bắt buộc dùng java 8 (Stream API, Lambda, Optional)
public class EmployeeBusiness {
    List<Employee> employeeList;
    List<Employee> employeeExcellent;

    private static EmployeeBusiness instance;

    private EmployeeBusiness() {
    }

    public static EmployeeBusiness getInstance() {
        if (instance == null) {
            instance = new EmployeeBusiness();
        }
        return instance;
    }

    // Hiển thị danh sách dạng bảng
    public void displayList(){
        System.out.printf("%-20s%-20s%-20s%-20s\n", "Tên nhân viên", "Mã nhân viên", "Tuổi", "Lương");
        for (Employee employee : employeeList) {
            System.out.printf("%-20s%-20s%-20d%-20.2f\n", employee.getEmpName(), employee.getEmpId(), employee.getAge(), employee.getSalary());
        }
    }

    // nếu trùng empId thì in ra lỗi
    public void addEmployee(Employee employee) {
        for (Employee emp : employeeList) {
            if (emp.getEmpId().equals(employee.getEmpId())) {
                System.out.println("Error: Id của nhân viên đã có trong hệ thống.");
                return;
            }
        }
        employeeList.add(employee);
    }

    // nếu kích thước danh sách không đổi sau khi xoá (Không tìm thấy id) in ra lỗi
    public void removeEmployee(String empId) {
        int initialSize = employeeList.size();
        employeeList.removeIf(employee -> employee.getEmpId().equals(empId));
        if (employeeList.size() == initialSize) {
            System.out.println("Error: Không tìm thấy nhân viên với ID: " + empId);
        }
    }

    // cho người dùng chọn thông tin nào muốn cập nhật (empName, age, salary) và nhập giá trị mới
    public void updateEmployee(Employee updatedEmployee, Scanner scanner) {
        String update;
        for (Employee emp : employeeList) {
            if (emp.getEmpId().equals(updatedEmployee.getEmpId())) {
                System.out.println("Mời chọn thông tin muốn cập nhật");
                update = scanner.nextLine().toLowerCase();
                switch (update) {
                    case "name":
                        emp.setEmpName(updatedEmployee.getEmpName());
                        break;
                    case "age":
                        emp.setAge(updatedEmployee.getAge());
                        break;
                    case "salary":
                        emp.setSalary(updatedEmployee.getSalary());
                        break;
                    case "exit":
                        return;
                    default:
                        System.out.println("Thông tin không hợp lệ.");
                        break;
                }
                return;
            }
        }
        System.out.println("Error: Không tìm thấy nhân viên với ID: " + updatedEmployee.getEmpId());
    }

    // lấy danh sách nhân viên
    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    // sắp xếp theo lương giảm dần trả ề danh sách
    public List<Employee> sortSalary(){
        List<Employee> result = employeeList.stream().sorted((s1,s2)-> Double.compare(s2.getSalary(), s1.getSalary())).toList();
        if(!result.isEmpty()){
            System.out.println("Không có nhân viên để so sánh.");
        }
        return result;
    }

    // tìm kiếm tương đối theo nhân viên theo tên Không phân biệt hoa thường trả ề danh sách đối tượng tìm thấy, không thấyin ra lỗi
    public List<Employee> searchNameEmployee(String name){
        List<Employee> result = employeeList.stream().filter(e -> e.getEmpName().toLowerCase().contains(name.toLowerCase())).toList();
        if(!result.isEmpty()){
            System.out.println("Không tìm thấy nhân viên.");
        }
        return result;
    }

    //lọc nhân viên có lương >=15000000 đưa vào danh sách nhân viên xuất sắc
    public void filterExcellentEmployees() {
        // lọc bằng dùng stream-> lọc ằng filter-> chuyển thành list-> đưa vào danh sách nhân vin xuất sắc
        employeeList.stream().filter(e -> e.getSalary() >= 15000000).toList().forEach(employeeExcellent::add);
        for (Employee e : employeeExcellent){
            e.displayData();
        }
    }
}

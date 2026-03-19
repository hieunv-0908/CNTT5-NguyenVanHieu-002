package presentation;

import Business.EmployeeBusiness;
import entity.Employee;

import java.util.List;
import java.util.Scanner;

public class EmployeeManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        EmployeeBusiness employeeBusiness = EmployeeBusiness.getInstance();
        while (true){
            System.out.printf("\n ------------------- Quản lý nhân viên -------------------------- \n 1. Hiển thị danh sách toàn bộ nhân viên \n 2. Thêm nhân viên mới \n 3. Cập nhật thông tin nhân viên \n 4. Xoá nhân viên \n 5. Tìm kiếm nhân viên (theo tên) \n 6. Lọc danh sách nhân viên xuâ sắc \n 7.Sắp xếp nhân viên giảm dần theo tên \n 0. Dừng chương trình \n---------------------------------------------\n Mời nhập lựa chọn: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice){
                case 1:
                    try{
                    System.out.println("Danh sách nhân viên: ");
                    employeeBusiness.displayList();
                    }catch (NullPointerException e){
                        System.out.println("Danh sách nhân viên đang trống.");
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Thêm nhân viên mới: ");
                        Employee newEmployee = new Employee();
                        newEmployee.inputData(sc);
                        employeeBusiness.addEmployee(newEmployee);
                        System.out.println("Thêm nhân viên thành công!");
                        newEmployee.displayData();
                    } catch (Exception e) {
                        System.out.println("Lỗi khi thêm nhân viên. Vui lòng thử lại.");
                    }
                        break;
                case 3:
                    try {
                        System.out.println("Cập nhật thông tin nhân viên: ");
                        Employee updatedEmployee = new Employee();
                        System.out.print("Nhập ID nhân viên cần cập nhật: ");
                        updatedEmployee.setEmpId(sc.nextLine());
                        employeeBusiness.updateEmployee(updatedEmployee, sc);
                    } catch (Exception e) {
                        System.out.println("Cập nhật thành công");
                    }
                    break;
                case 4:
                    try {
                        System.out.print("Nhập ID nhân viên cần xoá: ");
                        String empIdToRemove = sc.nextLine();
                        employeeBusiness.removeEmployee(empIdToRemove);
                    } catch (Exception e) {
                        System.out.println("Không tìm thấy nhân viên.");
                    }
                    break;
                case 5:
                    try {
                        System.out.println("Tìm kiếm nhân viên theo tên: ");
                        System.out.print("Nhập tên nhân viên cần tìm: ");
                        String nameToSearch = sc.nextLine();
                        employeeBusiness.searchNameEmployee(nameToSearch);
                    } catch (Exception e) {
                        System.out.println("Không tìm thấy nhân viên.");
                    }

                    break;
                case 6:
                    try {
                        System.out.println("Lọc danh sách nhân viên xuất sắc: ");
                        employeeBusiness.filterExcellentEmployees();
                    } catch (RuntimeException e) {
                        System.out.println("Danh sách nhân viên trống.");
                    }
                    break;
                case 7: {
                    try {
                        List<Employee> employeeListTemp;
                        System.out.println("Sắp xếp nhân viên giảm dần theo lương: ");
                        employeeListTemp = employeeBusiness.sortSalary();
                        for (Employee employee : employeeListTemp) {
                            System.out.printf("%-20s%-20s%-20d%-20.2f\n", employee.getEmpName(), employee.getEmpId(), employee.getAge(), employee.getSalary());
                        }
                    } catch (Exception e) {
                        System.out.println("Danh sách nhân viên trống.");
                    }
                }
                    break;
                case 0:
                    System.out.println("Dừng trương trình.");
                    return;
                default:
                    System.out.println("Lựa chon không hợp lệ");
                    break;
            }
        }
    }
}

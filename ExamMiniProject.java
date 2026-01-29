package ss05;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExamMiniProject {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        String[] students = new String[100];

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. In danh sách MSSV");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xoá");
            System.out.println("5. Tìm kiếm (regex)");
            System.out.println("6. Thoát");
            System.out.print("Nhập lựa chọn: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    displayStudent(students);
                    break;
                case 2:
                    addStudent(students);
                    break;
                case 3:
                    updateStudents(students);
                    break;
                case 4:
                    deleteStudent(students);
                    break;
                case 5:
                    searchStudent(students);
                    break;
                case 6:
                    System.out.println("Thoát chương trình thành công.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 6);
    }

    public static void displayStudent(String[] students) {
        boolean empty = true;
        System.out.println("Danh sách MSSV:");
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null) {
                System.out.printf("student[%d]: %s\n", i, students[i]);
                empty = false;
            }
        }
        if (empty) {
            System.out.println("Chưa có sinh viên nào.");
        }
    }

    public static boolean checkRegex(String student) {
        return student.matches("^B\\d{7}$");
    }

    public static void addStudent(String[] students) {

        String mssv;

        do {
            System.out.print("Nhập MSSV (Bxxxxxxx): ");
            mssv = sc.nextLine();
        } while (!checkRegex(mssv));

        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = mssv;
                System.out.println("Thêm thành công.");
                return;
            }
        }
        System.out.println("Danh sách đã đầy.");
    }

    public static void updateStudents(String[] students) {
        System.out.print("Nhập vị trí cần sửa: ");
        int index = sc.nextInt();
        

        if (index < 0 || index >= students.length || students[index] == null) {
            System.out.println("Vị trí không hợp lệ.");
            return;
        }

        System.out.print("Nhập MSSV mới: ");
        String mssv = sc.nextLine();

        if (checkRegex(mssv)) {
            students[index] = mssv;
            System.out.println("Cập nhật thành công.");
        } else {
            System.out.println("MSSV sai định dạng.");
        }
    }

    public static void deleteStudent(String[] students) {
        sc.nextLine();
        System.out.print("Nhập MSSV cần xoá: ");
        String mssv = sc.nextLine();

        for (int i = 0; i < students.length; i++) {
            if (mssv.equals(students[i])) {
                for (int j = i; j < students.length - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[students.length - 1] = null;
                System.out.println("Xoá thành công.");
                return;
            }
        }
        System.out.println("Không tìm thấy MSSV.");
    }

    public static void searchStudent(String[] students) {
        
        System.out.print("Nhập chuỗi cần tìm: ");
        String keyword = sc.nextLine();

        Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
        boolean found = false;

        for (int i = 0; i < students.length; i++) {
            if (students[i] != null) {
                Matcher matcher = pattern.matcher(students[i]);
                if (matcher.find()) {
                    System.out.printf("student[%d]: %s\n", i, students[i]);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy MSSV nào phù hợp.");
        }
    }


}


import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] arg) {
        Manager.studentList.add(new Student("1", "Thanh Lam", "Spring_2019", "Java"));
        Manager.studentList.add(new Student("2", "Thanh Quang", "Fall_2021", "C/C++"));
        Manager.studentList.add(new Student("3", "Thanh Hai", "Summer_2020", ".Net"));
        int count = 0;
        Validation validation = new Validation();
        while(true) {
            Manager.menu();
            int choice = validation.checkInputLimit(1, 5);
            switch (choice) {
                case 1:
                    Manager.CreateStudent(count);
                    break;
                case 2:
                    Manager.findAndSort();
                    break;
                case 3 :
                    Manager.updateOrDelete(count);
                    break;
                case 4:
                    Manager.report();
                    break;
                case 5:
                    return;
            }
        }
    }
}

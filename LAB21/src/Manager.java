import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Manager {
    public static ArrayList<Student> studentList = new ArrayList<>();

    public static final Scanner sc = new Scanner(System.in);

    public static void menu() {

        System.out.println("1. Create");
        System.out.println("2. Find and sort");
        System.out.println("3. Update/Delete");
        System.out.println("4. Report");
        System.out.println("5. Exit");
        System.out.println("Enter your choice: ");
    }

    public static void CreateStudent(int count) {

        if (count > 10) {
            System.out.println("Do you want to continue (Y/N)?");
            if (!Validation.checkInputYN()) {
                return;
            }
        }
        //loop until user input not duplicate
        while (true) {
            System.out.println("Enter id: ");
            String id = Validation.checkInputString();
            String name = Validation.checkIdExist(studentList, id);
            if (name != null) {
                System.out.println("Student name: " + name);
            } else {
                System.out.print("Enter student name: ");
                name = Validation.checkInputString();
            }
            System.out.print("Enter semester: ");
            String sem = Validation.checkInputString();
            System.out.print("Enter course: ");
            String course = Validation.checkInputCourse();
            //check student exist
            if (Validation.checkStudentExist(studentList, id, name, sem, course)) {
                studentList.add(new Student(id, name, sem, course));
                count++;
                System.out.println("Add student success!");
                return;
            }
            System.err.println("Duplicate student");
        }
    }

    //list student by name
    public static ArrayList<Student> getListStudentFindByName(ArrayList<Student> studentList) {
        ArrayList<Student> listStudentFindByName = new ArrayList<>();
        System.out.println("Enter name to check: ");
        String name = Validation.checkInputString();
        for (Student student : studentList) {
            if (student.getName().contains(name)) {
                listStudentFindByName.add(student);
            }
        }
        return listStudentFindByName;
    }

    //user find and sort
    public static void findAndSort() {
        //check list empty
        if (studentList.isEmpty()) {
            System.err.println("Empty list");
            return;
        } else {
            ArrayList<Student> listStudentFindByName = getListStudentFindByName(studentList);
            if (listStudentFindByName.isEmpty()) {
                System.out.println("Not exist!");
            } else {
                Collections.sort(listStudentFindByName);
                System.out.printf("%-15s%-15s%-15s\n", "Student name", "semester", "Course Name");
                //loop from the first element to the last element
                for (Student student : listStudentFindByName) {
                    student.print();
                }
            }
        }
    }

    //get list student find by id
    public static ArrayList<Student> getListStudentFindById(ArrayList<Student> studentList, String id) {
        ArrayList<Student> getListStudentFindById = new ArrayList<>();
        for (Student student : studentList) {
            if (id.equalsIgnoreCase(student.getId())) {
                getListStudentFindById.add(student);
            }
        }
        return getListStudentFindById;
    }

    //get student user want to update/delete in list found
    public static Student getStudentByListFound(ArrayList<Student> listStudentFindByName) {
        System.out.println("List student found: ");
        int count = 1;
        System.out.printf("%-10s%-15s%-15s%-15s\n", "Number", "Student name",
                "semester", "Course Name");
        //display list student found
        for (Student student : listStudentFindByName) {
            System.out.printf("%-10d%-15s%-15s%-15s\n", count,
                    student.getName(), student.getSemester(), student.getCourseName());
            count++;
        }
        System.out.println("Enter number: ");
        int choice = Validation.checkInputLimit(1, listStudentFindByName.size());
        return listStudentFindByName.get(choice - 1);
    }

    //allow user update or delete
    public static void updateOrDelete(int count) {
        if (studentList.isEmpty()) {
            System.err.println("List empty");
            return;
        }
        System.out.println("Enter id: ");
        String id = Validation.checkInputString();
//        System.out.print("Do you want to update (U) or delete (D) student: ");
        ArrayList<Student> listStudentFindByName = getListStudentFindById(studentList, id);
        //check list empty
        if (listStudentFindByName.isEmpty()) {
            System.out.println("Not found student");
            return;
        } else {
            Student student = getStudentByListFound(listStudentFindByName);
            System.out.print("Do you want to update (U) or delete (D) student: ");
            //check user want to U or D
            if (Validation.checkInputUD()) {
                System.out.println("Enter id: ");
                String idStudent = sc.nextLine();
                if (idStudent.equals("")) {
                    idStudent = student.getId();
                }
                System.out.println("Enter student name: ");
                String nameStudent = sc.nextLine();
                if (nameStudent.equals("")) {
                    nameStudent = student.getName();
                }
                updateIdAndName(studentList, idStudent, student.getId(), nameStudent, student.getName());
                System.out.println("Enter semester: ");
                String semester = Validation.checkInputString();
                System.out.println("Enter name course: ");
                String courseName = Validation.checkInputCourse();
                //check user change or not
                if (!Validation.checkChangeInformation(student, idStudent, nameStudent, semester, courseName)) {
                    System.out.println("Nothing change!");
                }
                //check student exist or not
                if (Validation.checkStudentExist(studentList, idStudent, nameStudent, semester, courseName)) {
                    student.setId(idStudent);
                    student.setName(nameStudent);
                    student.setSemester(semester);
                    student.setCourseName(courseName);
                    System.err.println("Update success!");
                }
                return;
            } else {
                studentList.remove(student);
                count--;
                System.err.println("Delete success");
                return;
            }
        }
    }

    public static void updateIdAndName(ArrayList<Student> studentList, String idUpdate, String id, String nameUpdate, String name) {
        for (int i = 0; i < studentList.size(); i++) {
            Student currentStudent = studentList.get(i);
            if (currentStudent.getId().equals(id)) {
                currentStudent.setId(idUpdate);
                currentStudent.setName(nameUpdate);
            }
        }
    }

    public static void report() {
        if (studentList.isEmpty()) {
            System.err.println("List empty");
            return;
        }
        ArrayList<Report> reportList = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            int total = 0;
            String id = studentList.get(i).getId();
            String courseName = studentList.get(i).getCourseName();
            String studentName = studentList.get(i).getName();
            for (Student studentCountTotal : studentList) {
                if (id.equalsIgnoreCase(studentCountTotal.getId())) {
                    total++;
                }
            }
            if (Validation.checkReportExist(reportList, studentName, courseName, total)) {
                reportList.add(new Report(studentList.get(i).getName(), courseName, total));
            }

        }
        //print report
        for (int i = 0; i < reportList.size(); i++) {
            System.out.printf("%-15s|%-10s|%-5d\n", reportList.get(i).getStudentName(),
                    reportList.get(i).getCourseName(), reportList.get(i).getTotalCourse());
        }
    }

}

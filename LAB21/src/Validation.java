import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.Scanner;

public class Validation {
    public static final Scanner sc = new Scanner(System.in);

    public static int checkInputLimit(int min, int max) {
        while (true) {
            try{
                int result = Integer.parseInt(sc.nextLine().trim());
                if(result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            }catch (NumberFormatException ex){
                System.err.println("Please enter the number between [ " + min + ", "+ max+" ]");
                System.out.println("Enter again");
            }
        }
    }

    public static String checkInputString() {
        while (true) {
            String result = sc.nextLine().trim();
            if(result.isEmpty()) {
                System.err.println("Can not be empty");
                System.out.println("Please enter again!");
            }else
                return result;
        }
    }

    public static boolean checkInputYN() {
        while (true) {
            String result = checkInputString();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            if (result.equalsIgnoreCase("N")) {
                return true;
            }
            System.err.println("Syntax error, try again");
            System.out.println("Enter again");
        }
    }

    public static String checkInputCourse() {
        while (true) {
            String result = checkInputString();
            if(result.equalsIgnoreCase("java")
                    || result.equalsIgnoreCase(".net")
                    || result.equalsIgnoreCase("c/c++")) {
                return result;
            }
            System.err.println("There are only 3 course: Java, .Net and C/C++");
            System.out.println("Enter again!");
        }
    }
    public static boolean checkInputUD (){
        while (true) {
            String result = checkInputString();
            if (result.equalsIgnoreCase("U")) {
                return true;
            }
            if (result.equalsIgnoreCase("D")) {
                return false;
            }

            System.err.println("Please enter U/u or D/d");
            System.out.println("Enter again");
        }
    }
    //check id exist
    public static String checkIdExist(ArrayList<Student> listStudent, String id) {
        for(Student st:listStudent) {
            if(id.equalsIgnoreCase(st.getId())) {
                return st.getName();
            }
        }
        return null;
    }

    public static boolean checkStudentExist(ArrayList<Student> listStudent, String id, String name, String semester, String courseName){
        for(Student st: listStudent) {
            if(id.equals(st.getId())
                    && name.equalsIgnoreCase(st.getName())
                    && semester.equalsIgnoreCase(st.getSemester())
                    && courseName.equalsIgnoreCase(st.getCourseName())) {
                return false;
            }
        }
        return true;
    }
    //check report exist
    public static boolean checkReportExist(ArrayList<Report> listReport, String name, String course, int total) {
        for(Report report : listReport) {
            if(name.equalsIgnoreCase(report.getCourseName())
                    && course.equalsIgnoreCase(report.getCourseName())
                    && total == report.getTotalCourse()) {
                return false;
            }
        }
        return true;
    }

    //check user change or not
    public static boolean checkChangeInformation(Student student, String id, String name,
                                                 String semester, String course) {
        if(id.equalsIgnoreCase(student.getId())
                && name.equalsIgnoreCase(student.getName())
                && semester.equalsIgnoreCase(student.getSemester())
                && course.equalsIgnoreCase(student.getCourseName())) {
            return false;
        }
        return true;
    }
}

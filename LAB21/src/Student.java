public class Student implements Comparable<Student>{
    private String id;
    private String name;
    private String semester;
    private String courseName;

    public Student() {
    }

    public Student(String id, String name, String semester, String courseName) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.courseName = courseName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        courseName = courseName;
    }

    @Override
    public int compareTo(Student o) {
        return o.getName().compareTo(this.getName());
    }

    public void print() {
        System.out.printf("%-15s%-15s%-15s\n", name, semester, courseName);
    }
}

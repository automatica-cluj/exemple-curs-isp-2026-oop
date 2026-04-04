package utcluj.isp.curs5.others.studentexample;

public class GradeManagerExample {
    public static void main(String[] args) {
        Student s1 = new Student("AAA","BBB");
        GradesManagerSystem gs  = new GradesManagerSystem();
        gs.addStudent(s1);

        gs.remove(new Student("AAA","BBB"));

        Student x = gs.searchByName("AAA");
        x.setDepartment("AUT");


        System.out.println(gs.searchByName("AAA").calculateAvarageGrade());
    }
}

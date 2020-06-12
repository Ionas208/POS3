package at.htlkaindorf.dev;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListCompTest {


    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("0"));
        students.add(new Student("1"));
        students.add(new Student("2"));
        students.add(new Student("3"));
        students.add(new Student("4"));

        students.sort((o1,o2) -> (o1.getFirstname()).compareTo(o2.getFirstname()));
    }
}

package Stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author KIKOFranklin
 * @create 2021/4/16 0016 21:36
 */
public class Test {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("1","1"));
        list.add(new Student("2","2"));
        list.add(new Student("3","3"));
        List<Student> newList = list.stream().peek(Student::getName).filter(t -> t.getName().equals("1")).collect(Collectors.toList());
        newList.forEach(Student::getName);
    }
}

class Student{
    private String name;
    private String pwd ;

    public Student() {
    }

    public Student(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public String getName() {
        System.out.println(name);
        return name;
    }

    public String getName1() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}

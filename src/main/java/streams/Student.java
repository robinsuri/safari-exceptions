package streams;

import java.util.Arrays;
import java.util.List;

public class Student {
  private String name;
  private List<String> classes;

  public Student(String name, String ... classes) {
    this.name = name;
    this.classes = Arrays.asList(classes);
  }

  public String getName() {
    return name;
  }

  public List<String> getClasses() {
    return classes;
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", classes=" + classes +
        '}';
  }
}
